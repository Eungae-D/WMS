package com.wms.domain.inputWarehouseDetail.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InputWarehouseDetailCompleteDTO {
    @NotNull(message = " ID는 필수입니다.")
    private Long inputWarehouseDetailId;
}
