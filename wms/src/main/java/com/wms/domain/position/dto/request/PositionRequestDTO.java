package com.wms.domain.position.dto.request;

import com.wms.domain.position.entity.Position;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PositionRequestDTO {

    @NotBlank(message = "직급코드가 존재하지 않습니다.")
    private String position_code;

    @NotBlank(message = "직급명이 존재하지 않습니다.")
    private String position_name;

    public Position toEntity(){
        return Position.builder()
                .position_code(this.position_code)
                .position_name(this.position_name)
                .build();
    }
}
