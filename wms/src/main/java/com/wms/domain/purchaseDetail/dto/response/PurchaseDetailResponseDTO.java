package com.wms.domain.purchaseDetail.dto.response;

import com.wms.domain.purchaseDetail.entity.PurchaseDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PurchaseDetailResponseDTO {
    private Long id;
    private Long itemId;            // 품목 id
    private String itemCode;        // 품목 코드
    private String itemName;        // 품목명
    private Long amount;            // 발주 수량
    private Long unitPrice;          // 구매 단가
    private Long totalAmount;       // 합계 (구매 단가 * 발주 수량)
    private String warehouseName;    // 창고이름

    public static PurchaseDetailResponseDTO fromEntity(PurchaseDetail purchaseDetail) {
        return PurchaseDetailResponseDTO.builder()
                .id(purchaseDetail.getId())
                .warehouseName(purchaseDetail.getWarehouse().getName())
                .itemId(purchaseDetail.getItem().getId())
                .itemCode(purchaseDetail.getItem().getCode())
                .itemName(purchaseDetail.getItem().getName())
                .amount(purchaseDetail.getAmount())
                .unitPrice(purchaseDetail.getItem().getUnitPrice())
                .totalAmount(purchaseDetail.getItem().getUnitPrice() * purchaseDetail.getAmount())
                .build();
    }
}
