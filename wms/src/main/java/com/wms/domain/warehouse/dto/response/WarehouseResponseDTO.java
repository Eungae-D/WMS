package com.wms.domain.warehouse.dto.response;

import com.wms.domain.warehouse.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class WarehouseResponseDTO {
    private Long id;
    private String code;
    private String name;

    public static WarehouseResponseDTO fromEntity(Warehouse warehouse) {
        return WarehouseResponseDTO.builder()
                .id(warehouse.getId())
                .code(warehouse.getCode())
                .name(warehouse.getName())
                .build();
    }
}
