package com.wms.domain.client.dto.request;

import com.wms.domain.client.entity.Category;
import com.wms.domain.client.entity.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ClientRequestDTO {

    @NotBlank(message = "거래처코드가 존재하지 않습니다.")
    private String code;

    @NotNull(message = "카테고리가 존재하지 않습니다.")
    private Category category;

    @NotBlank(message = "거래처 이름이 존재하지 않습니다.")
    private String name;

    @NotBlank(message = "대표자명이 존재하지 않습니다.")
    private String owner;

    @NotBlank(message = "전화번호가 존재하지 않습니다.")
    private String tel;

    private String fax;

    @NotBlank(message = "은행 정보가 존재하지 않습니다.")
    private String bank;

    @NotBlank(message = "계좌번호가 존재하지 않습니다.")
    private String account;

    @NotBlank(message = "우편번호가 존재하지 않습니다.")
    private String zipCode;

    @NotBlank(message = "주소가 존재하지 않습니다.")
    private String address1;

    @NotBlank(message = "상세주소가 존재하지 않습니다.")
    private String address2;

    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    @NotBlank(message = "이메일이 존재하지 않습니다.")
    private String email;

    @NotBlank(message = "사업자 등록번호가 존재하지 않습니다.")
    private String businessNumber;

    public Client toEntity() {
        return Client.builder()
                .code(this.code)
                .category(this.category)
                .name(this.name)
                .owner(this.owner)
                .tel(this.tel)
                .fax(this.fax)
                .bank(this.bank)
                .account(this.account)
                .zipCode(this.zipCode)
                .address1(this.address1)
                .address2(this.address2)
                .email(this.email)
                .businessNumber(this.businessNumber)
                .build();
    }
}
