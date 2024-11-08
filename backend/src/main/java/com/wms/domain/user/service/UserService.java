package com.wms.domain.user.service;

import com.wms.domain.user.dto.request.EmailRequestDTO;
import com.wms.domain.user.dto.request.SignUpRequestDTO;
import com.wms.domain.user.dto.response.UserListResponseDTO;
import jakarta.validation.Valid;

import java.util.List;


public interface UserService {

    // 이메일 중복 체크
    boolean emailCheck(@Valid EmailRequestDTO emailRequestDTO);
    // 일반 회원가입
    void register(@Valid SignUpRequestDTO signUpRequestDTO);

    // 직원 삭제
    void deleteUser(Long userId);

    // 직원 목록 가져오기
    List<UserListResponseDTO> listUsers();

}
