package com.wms.domain.user.service.impl;

import com.wms.domain.exception.exception.UserException;
import com.wms.domain.exception.responseCode.UserExceptionResponseCode;
import com.wms.domain.user.dto.request.EmailRequestDTO;
import com.wms.domain.user.dto.request.SignUpRequestDTO;
import com.wms.domain.user.entity.User;
import com.wms.domain.user.repository.UserRepository;
import com.wms.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    // 이메일 중복확인
    @Override
    @Transactional(readOnly = true)
    public boolean emailCheck(EmailRequestDTO emailRequestDTO){

        return userRepository.existsByEmail(emailRequestDTO.getEmail());
    }

    // 회원가입
    @Override
    @Transactional
    public void register(SignUpRequestDTO signUpRequestDTO){

        //이메일 중복 확인
        if(userRepository.existsByEmail(signUpRequestDTO.getEmail())){
            throw new UserException(UserExceptionResponseCode.EXISTS_USER, "이미 존재하는 유저입니다.");
        }

        User user = signUpRequestDTO.toEntity(encoder.encode(signUpRequestDTO.getPassword()));

        userRepository.save(user);
    }
}
