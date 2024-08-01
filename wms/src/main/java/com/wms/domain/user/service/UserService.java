package com.wms.domain.user.service;

import com.wms.domain.user.dto.request.EmailRequestDTO;
import com.wms.domain.user.dto.request.SignUpRequestDTO;
import jakarta.validation.Valid;


public interface UserService {

    // 이메일 중복 체크
    boolean emailCheck(@Valid EmailRequestDTO emailRequestDTO);
    // 일반 회원가입
    void register(@Valid SignUpRequestDTO signUpRequestDTO);
}
