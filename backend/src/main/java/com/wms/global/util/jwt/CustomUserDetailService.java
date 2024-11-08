package com.wms.global.util.jwt;

import com.wms.global.exception.exception.UserException;
import com.wms.global.exception.responseCode.UserExceptionResponseCode;
import com.wms.domain.user.entity.User;
import com.wms.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    //UserDetails인터페이스를 반환해야 하지만 CustomUserDetails는 UserDetails 인터페이스를 구현하고 있는 클래스이므로 가능하다.
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException(UserExceptionResponseCode.USER_NOT_FOUND,Long.parseLong(email)+"유저를 찾지 못했습니다."));
        return new CustomUserDetails(user);
    }

}
