package com.wms.global.util.oauth2;

import com.wms.domain.user.entity.Role;
import com.wms.domain.user.entity.SocialType;
import com.wms.domain.user.entity.User;
import com.wms.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }

        else {

            return null;
        }

        //db에 유저가 있는지 판단
        Optional<User> byUser = userRepository.findByEmail(oAuth2Response.getEmail());


        //회원가입 유저 없으면 (이부분 수정)
        if(byUser.isEmpty()){
            String username = oAuth2Response.getProvider()+" "+oAuth2Response.getName();

            User user = new User(SocialType.KAKAO,oAuth2Response.getEmail(),username, Role.GUEST,oAuth2Response.getProfileImage());

            userRepository.save(user);
            return new CustomOAuth2User(user);
        }else{
            //회원가입 유저 있으면 기존 카카오로그인 진행
            User user = byUser.get();
            if(user.getSocialType() == SocialType.KAKAO){
                return new CustomOAuth2User(user);
            }else{
                // 일반 로그인 사용자가 카카오 로그인 시도 시 에러 처리
                throw new OAuth2AuthenticationException("이미 해당 이메일로 가입된 계정이 있습니다. 일반 로그인을 이용해 주세요.");
            }
        }
    }
}
