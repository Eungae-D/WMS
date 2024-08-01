package com.wms.domain.token.service.impl;

import com.wms.domain.exception.exception.TokenException;
import com.wms.domain.exception.exception.UserException;
import com.wms.domain.exception.responseCode.TokenExceptionResponseCode;
import com.wms.domain.exception.responseCode.UserExceptionResponseCode;
import com.wms.domain.token.entity.Token;
import com.wms.domain.token.repository.TokenRepository;
import com.wms.domain.token.service.TokenService;
import com.wms.domain.user.entity.User;
import com.wms.domain.user.repository.UserRepository;
import com.wms.global.util.jwt.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtUtil jwtUtil;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void reGenerateToken(HttpServletRequest request, HttpServletResponse response) {
        //get refresh token
        String refresh = null;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refreshToken")) {
                refresh = cookie.getValue();
            }
        }

        if (refresh == null) {
            throw new TokenException(TokenExceptionResponseCode.REFRESH_TOKEN_NULL, "REFRESH_TOKEN이 NULL입니다.");
        }

        //expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            throw new TokenException(TokenExceptionResponseCode.REFRESH_TOKEN_EXPIRED, "REFRESH_TOKEN이 만료되었습니다.");
        }

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);

        if (!category.equals("refreshToken")) {
            throw new TokenException(TokenExceptionResponseCode.INVALID_REFRESH_TOKEN, "유효하지 않은 REFRESH_TOKEN입니다.");
        }

        boolean isExist = tokenRepository.existsByRefreshToken(refresh);
        if(!isExist){
            throw new TokenException(TokenExceptionResponseCode.DOES_NOT_MATCH_REFRESH_TOKEN, "REFRESH_TOKEN이 일치하지 않습니다.");
        }

        Long userId = jwtUtil.getUserId(refresh);
        String role = jwtUtil.getRole(refresh);

        //make new AccessToken
        String newAccessToken = jwtUtil.createAccessToken("accessToken", userId, role, 2*60*60*1000L);
        String newRefreshToken = jwtUtil.createRefreshToken("refreshToken", userId, 30*24*60*60*1000L);

        User user = userRepository.findById(userId).orElseThrow(()-> new UserException(UserExceptionResponseCode.USER_NOT_FOUND, userId + "번 유저를 찾지 못했습니다."));
        tokenRepository.deleteByRefreshToken(refresh);
        Token refreshEntity = Token.toEntity(user, newRefreshToken,  LocalDateTime.now().plusDays(30));
        tokenRepository.save(refreshEntity);

        response.addCookie(createCookie("accessToken", newAccessToken));
        //refreshToken_rotate
        response.addCookie(createCookie("refreshToken", newRefreshToken));
    }

    private Cookie createCookie(String key, String value){
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
//        cookie.setSecure(true); //HTTPS를 사용하는 경우에 true로 설정
//        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
