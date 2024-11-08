package com.wms.domain.rack.dto.request;

import com.wms.domain.area.entity.Area;
import com.wms.domain.rack.entity.Rack;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RackRequestDTO {
    @NotBlank(message = "랙 코드가 존재하지 않습니다.")
    private String code;

    @NotBlank(message = "랙명이 존재하지 않습니다.")
    private String name;

    @NotNull(message = "구역 ID가 존재하지 않습니다.")
    private Long areaId;

    public Rack toEntity(Area area){
        return Rack.builder()
                .code(this.code)
                .name(this.name)
                .area(area)
                .build();
    }
}
