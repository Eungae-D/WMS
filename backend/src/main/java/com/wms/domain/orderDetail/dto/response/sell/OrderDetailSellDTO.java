package com.wms.domain.orderDetail.dto.response.sell;

import com.wms.domain.inventory.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrderDetailSellDTO {
    private String lotCode;       // Lot 코드
    private int inventoryAmount; // 재고 수량

    public static OrderDetailSellDTO fromEntity(Inventory inventory) {
        return OrderDetailSellDTO.builder()
                .lotCode(inventory.getLot().getLotNumber())
                .inventoryAmount(inventory.getQuantity())
                .build();
    }
}
