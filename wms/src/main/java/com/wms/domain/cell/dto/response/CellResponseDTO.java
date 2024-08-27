package com.wms.domain.cell.dto.response;

import com.wms.domain.cell.entity.Cell;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CellResponseDTO {
    private Long id;
    private String code;
    private String name;
    private Long rackId;

    public static CellResponseDTO fromEntity(Cell cell) {
        return CellResponseDTO.builder()
                .id(cell.getId())
                .code(cell.getCode())
                .name(cell.getName())
                .rackId(cell.getRack().getId())
                .build();
    }
}
