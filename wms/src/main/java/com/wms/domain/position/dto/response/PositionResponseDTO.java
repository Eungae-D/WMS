package com.wms.domain.position.dto.response;

import com.wms.domain.position.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PositionResponseDTO {
    private Long id;
    private String position_code;
    private String position_name;

    public static PositionResponseDTO fromEntity(Position position){
        return PositionResponseDTO.builder()
                .id(position.getId())
                .position_code(position.getPosition_code())
                .position_name(position.getPosition_name())
                .build();
    }
}
