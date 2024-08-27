package com.wms.domain.area.dto.request;

import com.wms.domain.area.entity.Area;
import com.wms.domain.warehouse.entity.Warehouse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AreaRequestDTO {
    @NotBlank(message = "구역 코드가 존재하지 않습니다.")
    private String code;

    @NotBlank(message = "구역명이 존재하지 않습니다.")
    private String name;

    @NotNull(message = "창고 ID가 존재하지 않습니다.")
    private Long warehouseId;

    public Area toEntity(Warehouse warehouse){
        return Area.builder()
                .code(this.code)
                .name(this.name)
                .warehouse(warehouse)
                .build();
    }
}
