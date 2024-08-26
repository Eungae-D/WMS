package com.wms.domain.cell.dto.request;

import com.wms.domain.cell.entity.Cell;
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
public class CellRequestDTO {

    @NotBlank(message = "셀 코드가 존재하지 않습니다.")
    private String code;

    @NotBlank(message = "셀명이 존재하지 않습니다.")
    private String name;

    @NotNull(message = "랙 ID가 존재하지 않습니다.")
    private Long rackId;

    public Cell toEntity(Rack rack){
        return Cell.builder()
                .code(this.code)
                .name(this.name)
                .rack(rack)
                .build();
    }
}
