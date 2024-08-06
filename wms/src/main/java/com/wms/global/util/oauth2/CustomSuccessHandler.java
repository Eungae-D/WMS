package com.wms.global.util.oauth2;


import com.wms.domain.token.entity.Token;
import com.wms.domain.token.repository.TokenRepository;
import com.wms.domain.user.entity.User;
import com.wms.global.util.jwt.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;
    private final TokenRepository tokenRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        User user = customUserDetails.getUser();
        Long userId = customUserDetails.getUserId();
        String username = customUserDetails.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        //유효기간 30분
        String accessToken = jwtUtil.createAccessToken("accessToken", userId, role,30*60*1000L);
        //유효기간 30일
        String refreshToken = jwtUtil.createRefreshToken("refreshToken", userId, 30*24*60*60*1000L);


        Token refreshEntity = Token.toEntity(user, refreshToken,  LocalDateTime.now().plusDays(30));
        tokenRepository.save(refreshEntity);

        response.addCookie(createCookie("accessToken", accessToken));
        response.addCookie(createCookie("refreshToken", refreshToken));
        response.sendRedirect("http://localhost:3000/");

        //성공 응답 JSON 작성
        String jsonResponse = String.format("{\"code\": \"%s\", \"message\": \"%s\", \"data\": {\"accessToken\": \"%s\", \"refreshToken\": \"%s\"}}",
                "SUCCESS", "로그인 성공.", accessToken, refreshToken);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }

    private Cookie createCookie(String key, String value){
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
//        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

}
