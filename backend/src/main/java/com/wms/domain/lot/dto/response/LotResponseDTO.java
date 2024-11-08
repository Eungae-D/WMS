package com.wms.domain.lot.dto.response;

import com.wms.domain.lot.entity.Lot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LotResponseDTO {
    private Long id;
    private String lotNumber;
    private String itemCode;
    private String itemName;
    private int totalQuantity;
    private int availableQuantity;

    public static LotResponseDTO fromEntity(Lot lot) {
        int totalQuantity = lot.getInventory().stream()
                .mapToInt(inventory -> inventory.getQuantity())
                .sum();

        int orderQuantity = lot.getInventory().stream()
                .mapToInt(inventory -> inventory.getOrderQuantity())
                .sum();

        int availableQuantity = totalQuantity - orderQuantity;  // 가용 재고 계산

        return LotResponseDTO.builder()
                .id(lot.getId())
                .lotNumber(lot.getLotNumber())
                .itemCode(lot.getItem().getCode())
                .itemName(lot.getItem().getName())
                .totalQuantity(totalQuantity)
                .availableQuantity(availableQuantity)  // 가용 재고 설정
                .build();
    }
}
