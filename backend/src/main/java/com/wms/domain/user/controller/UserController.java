package com.wms.domain.user.controller;

import com.wms.domain.user.dto.request.EmailRequestDTO;
import com.wms.domain.user.dto.request.SignUpRequestDTO;
import com.wms.domain.user.service.UserService;
import com.wms.global.util.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Iterator;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    // 이메일 중복 체크
    @PostMapping("/email-check")
    public ResponseEntity<ApiResponse<?>> emailCheck(@Valid @RequestBody EmailRequestDTO emailRequestDTO){
        boolean result = userService.emailCheck(emailRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(result, "이메일 중복 체크 성공. (true = 중복, false = 사용 가능)"));
    }

    // 회원가입
    @PostMapping(value = "/register", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<?>> register(@Validated @RequestPart("signUpRequest") SignUpRequestDTO signUpRequestDTO, @RequestPart(value = "profileImage", required = false) MultipartFile profileImage) {

        signUpRequestDTO = signUpRequestDTO.withProfileImage(profileImage);  // 프로필 이미지를 DTO에 설정
        userService.register(signUpRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("회원가입 성공."));
    }


    // 직원 삭제
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse<?>> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccessNoContent("직원 삭제 성공."));
    }

    // 직원 목록 가져오기
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> listUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(userService.listUsers(), "직원목록 조회 성공."));
    }

}
