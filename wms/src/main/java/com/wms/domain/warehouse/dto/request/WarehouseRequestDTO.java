package com.wms.domain.warehouse.dto.request;

import com.wms.domain.warehouse.entity.Warehouse;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WarehouseRequestDTO {

    @NotBlank(message = "창고코드가 존재하지 않습니다.")
    private String code;

    @NotBlank(message = "창고명이 존재하지 않습니다.")
    private String name;

    public Warehouse toEntity(){
        return Warehouse.builder()
                .code(this.code)
                .name(this.name)
                .build();
    }
}
