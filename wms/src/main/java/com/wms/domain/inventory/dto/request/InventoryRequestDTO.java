package com.wms.domain.inventory.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class InventoryRequestDTO {

    @NotNull(message = "아이템 ID가 존재하지 않습니다.")
    private Long itemId;
    //6
    @NotNull(message = "창고 ID가 존재하지 않습니다.")
    private Long warehouseId;
    //3
    @NotNull(message = "구역 ID가 존재하지 않습니다.")
    private Long areaId;
    //1
    @NotNull(message = "랙 ID가 존재하지 않습니다.")
    private Long rackId;
    //1
    @NotNull(message = "셀 ID가 존재하지 않습니다.")
    private Long cellId;
    //1
    @NotNull(message = "재고 수량이 존재하지 않습니다.")
    private int quantity;
}
