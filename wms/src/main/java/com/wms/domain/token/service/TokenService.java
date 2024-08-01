package com.wms.domain.token.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface TokenService {
    void reGenerateToken(HttpServletRequest request,HttpServletResponse response);
}
