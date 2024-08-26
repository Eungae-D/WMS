package com.wms.domain.inventory.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class InventoryRequestDTO {

    @NotNull(message = "사용자 ID가 존재하지 않습니다.")
    private Long userId;

    @NotNull(message = "아이템 ID가 존재하지 않습니다.")
    private Long itemId;

    @NotNull(message = "창고 ID가 존재하지 않습니다.")
    private Long warehouseId;

    @NotNull(message = "구역 ID가 존재하지 않습니다.")
    private Long areaId;

    @NotNull(message = "랙 ID가 존재하지 않습니다.")
    private Long rackId;

    @NotNull(message = "셀 ID가 존재하지 않습니다.")
    private Long cellId;

    @NotNull(message = "재고 수량이 존재하지 않습니다.")
    private int quantity;

    @NotNull(message = "체결 수량이 존재하지 않습니다.")
    private int orderQuantity;
}
