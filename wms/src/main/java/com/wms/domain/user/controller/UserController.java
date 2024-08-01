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
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO){
        userService.register(signUpRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("회원가입 성공."));
    }
    //연습
    @GetMapping("/asd")
    public ResponseEntity<ApiResponse<?>> check(){
//        boolean result = userService.emailCheck(emailRequestDTO);
        log.info("들어오나요");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();
        log.info(role);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccessNoContent("이메일 중복 체크 성공. (true = 중복, false = 사용 가능)"));
    }
}
