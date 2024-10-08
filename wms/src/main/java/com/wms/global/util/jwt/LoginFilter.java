package com.wms.global.util.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.domain.token.entity.Token;
import com.wms.domain.token.repository.TokenRepository;
import com.wms.domain.user.entity.User;
import com.wms.global.util.response.ApiResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final TokenRepository tokenRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //클라이언트 요청에서 username, password 추출
        String email = "";
        String password = "";

        try {
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> jsonRequest = mapper.readValue(sb.toString(), HashMap.class);
            email = jsonRequest.get("email");
            password = jsonRequest.get("password");

        } catch (IOException e) {
            e.printStackTrace();
        }

        //스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 한다.
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password, null);

        //token에 담은 검증을 위한 AuthenticationManager로 전달 -> AuthenticationManager는 AuthenticationProvider에게 인증을 위임
        //DaoAuthenticationProvider -> UserDetailsService를 사용하여 사용자를 로드하고 인증
        //CustomUserDetailService가 UserDetatilsService를 구현하여 loadUserByUsername을 로드
        return authenticationManager.authenticate(authToken);

    }

    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 된다.)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)throws IOException{
        CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();

        User user = customUserDetails.getUser();
        Long userId = customUserDetails.getUserId();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();
        //유효기간 2시간
        String accessToken = jwtUtil.createAccessToken("accessToken", userId, role,2*60*60*1000L);
        //유효기간 30일
        String refreshToken = jwtUtil.createRefreshToken("refreshToken", userId, 30*24*60*60*1000L);

        //+30일
        LocalDateTime expiredDate = LocalDateTime.now().plusDays(30);
        //유저가 있으면
        Optional<Token> existingTokenOpt = tokenRepository.findByUser(user);

        Token token;
        if (existingTokenOpt.isPresent()) {
            token = existingTokenOpt.get();
            token.updateToken(refreshToken, expiredDate);
        } else {
            token = Token.toEntity(user, refreshToken, expiredDate);
        }
        tokenRepository.save(token);

        response.addCookie(createCookie("accessToken", accessToken));
        response.addCookie(createCookie("refreshToken", refreshToken));

        //성공 응답 JSON 작성
        String jsonResponse = String.format("{\"code\": \"%s\", \"message\": \"%s\", \"data\": {\"accessToken\": \"%s\", \"refreshToken\": \"%s\"}}",
                "SUCCESS", "로그인 성공.", accessToken, refreshToken);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        String jsonResponse = String.format("{\"code\": \"%s\", \"message\": \"%s\"}", "LOGIN_FAILED", "로그인 실패. 잘못된 이메일 또는 비밀번호입니다.");

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
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
