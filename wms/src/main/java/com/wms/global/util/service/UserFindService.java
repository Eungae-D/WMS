package com.wms.global.util.service;

import com.wms.global.exception.exception.UserException;
import com.wms.global.exception.responseCode.UserExceptionResponseCode;
import com.wms.domain.user.entity.User;
import com.wms.domain.user.repository.UserRepository;
import com.wms.global.util.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFindService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    // 현재 유저 가져오기
//    public User getCurrentUser() {
//        Long currentUserId = jwtService.getCurrentUserId();
//        return userRepository.findById(currentUserId).orElseThrow(() -> new UserException(UserExceptionResponseCode.USER_NOT_FOUND, currentUserId + "번 유저를 찾지 못했습니다."));
//    }
}
