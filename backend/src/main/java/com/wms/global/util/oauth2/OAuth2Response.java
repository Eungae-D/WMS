package com.wms.global.util.oauth2;

public interface OAuth2Response {
    //제공자 (ex. naver, kakao)
    String getProvider();
    //제공자에서 발급해주는 아이디(번호)
    String getProviderId();
    //이메일
    String getEmail();
    //이름
    String getName();
    //사진
    String getProfileImage();
}
