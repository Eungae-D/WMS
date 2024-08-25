package com.wms.domain.rack.dto.response;

import com.wms.domain.rack.entity.Rack;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RackResponseDTO {
    private Long id;
    private String code;
    private String name;
    private Long areaId;

    public static RackResponseDTO fromEntity(Rack rack) {
        return RackResponseDTO.builder()
                .id(rack.getId())
                .code(rack.getCode())
                .name(rack.getName())
                .areaId(rack.getArea().getId())
                .build();
    }
}
