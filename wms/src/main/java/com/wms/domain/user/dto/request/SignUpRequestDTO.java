package com.wms.domain.user.dto.request;

import com.wms.domain.user.entity.Role;
import com.wms.domain.user.entity.SocialType;
import com.wms.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDTO {

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;



    public SignUpRequestDTO(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User toEntity (String passwordEncoding){
        return User.builder()
                .socialType(SocialType.GENERAL)
                .email(email)
                .password(passwordEncoding)
                .name(name)
                .role(Role.USER)
                .profileImage(null)
                .authorization(false)
                .build();
    }

}
