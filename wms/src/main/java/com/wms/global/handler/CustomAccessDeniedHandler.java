package com.wms.global.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.global.exception.responseCode.UserExceptionResponseCode;
import com.wms.global.util.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler { // 인증은 되었지만 권한이 없는 유저에 대해서 커스텀 핸들러 생성

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        UserExceptionResponseCode responseCode = UserExceptionResponseCode.FORBIDDEN_CLIENT;

        response.setStatus(responseCode.getStatus().value());
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponse<?> apiResponse = ApiResponse.createError(responseCode.getCode(), responseCode.getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        String responseJson = objectMapper.writeValueAsString(apiResponse);

        response.getWriter().write(responseJson);
    }
}
