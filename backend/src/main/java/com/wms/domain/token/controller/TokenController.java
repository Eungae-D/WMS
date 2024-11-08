package com.wms.domain.token.controller;

import com.wms.domain.token.service.TokenService;
import com.wms.global.util.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<Void>> refreshToken(HttpServletRequest request, HttpServletResponse response) {

        tokenService.reGenerateToken(request, response);

        return ResponseEntity.ok(ApiResponse.createSuccessNoContent("토큰 재발급 완료"));
    }
}
