package com.wms.domain.purchaseDetail.dto.request;

import com.wms.domain.item.entity.Item;
import com.wms.domain.purchaseDetail.entity.PurchaseDetail;
import com.wms.domain.purchaseDetail.entity.Status;
import com.wms.domain.purchaseSheet.entity.PurchaseSheet;
import com.wms.domain.warehouse.entity.Warehouse;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PurchaseDetailRequestDTO {

    @NotNull(message = "발주서 ID는 필수입니다.")
    private Long purchaseSheetId;

    @NotNull(message = "상품 ID는 필수입니다.")
    private Long itemId;

    @NotNull(message = "창고 ID는 필수입니다.")
    private Long warehouseId;

    @NotNull(message = "수량은 필수입니다.")
    private Long amount;

    // PurchaseDetail 엔티티로 변환하는 메서드
    public PurchaseDetail toEntity(PurchaseSheet purchaseSheet, Item item, Warehouse warehouse) {
        return PurchaseDetail.builder()
                .purchaseSheet(purchaseSheet)
                .item(item)
                .warehouse(warehouse)
                .amount(amount)
                .status(Status.EXPECTED)  // 발주 상세 상태는 EXPECTED로 기본 설정
                .build();
    }
}
