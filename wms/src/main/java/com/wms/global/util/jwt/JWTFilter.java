package com.wms.global.util.jwt;

import com.wms.domain.user.entity.Role;
import com.wms.domain.user.entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //회원가입, 이메일체크, 로그인은 jwt필터를 넘어가도 됌
        String path = request.getRequestURI();
        if (path.equals("/api/v1/users/register") || path.equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<String> accessTokenOptional = findAccessToken(request, "accessToken");

        // 토큰이 없다면 에러 메시지를 return
        if (accessTokenOptional.isEmpty()) {
            log.error("요청 경로 : " + request.getRequestURI() + " / 쿠키 없음.");
            sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "쿠키가 존재하지 않습니다.");
            return;
        }

        // 엑세스 토큰 추출
        String accessToken = accessTokenOptional.get();


        // 토큰이 access인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(accessToken);

        if (!category.equals("accessToken")) {

            log.error("category : { } / 토큰 형식이 잘못되었음.", category);

            //response status code
            sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "토큰형식이 잘못 되었습니다.");
            return;
        }

        // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {

            log.error("엑세스 토큰 만료됨: { }", accessToken);
            //response status code
            sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "액세스 토큰이 만료 되었습니다.");
            return;
        }


        // userId와 role 값을 획득
        Long userId = jwtUtil.getUserId(accessToken);
        String role = jwtUtil.getRole(accessToken);
        Role userRole = Role.fromString(role);

        // User 객체 생성
        User user = User.builder()
                .id(userId)
                .role(userRole)
                .build();

        // UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        // 스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        // 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

    // 쿠키에서 토큰 찾기
    private Optional<String> findAccessToken(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        return cookies == null ? Optional.empty() : Arrays.stream(cookies)
                .filter(cookie -> name.equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue);
    }

    // 공통 응답 메시지
    private void sendErrorResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.format("{\"error\": \"%s\"}", message));
    }
}
