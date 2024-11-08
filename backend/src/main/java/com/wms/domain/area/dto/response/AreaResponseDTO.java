package com.wms.domain.area.dto.response;

import com.wms.domain.area.entity.Area;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AreaResponseDTO {
    private Long id;
    private String code;
    private String name;
    private Long warehouseId;

    public static AreaResponseDTO fromEntity(Area area) {
        return AreaResponseDTO.builder()
                .id(area.getId())
                .code(area.getCode())
                .name(area.getName())
                .warehouseId(area.getWarehouse().getId())
                .build();
    }
}
