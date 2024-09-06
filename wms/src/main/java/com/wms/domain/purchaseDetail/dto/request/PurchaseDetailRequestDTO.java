package com.wms.domain.purchaseDetail.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PurchaseDetailRequestDTO {
    @NotNull(message = "상품 ID는 필수입니다.")
    private Long itemId;

    @NotNull(message = "창고 ID는 필수입니다.")
    private Long warehouseId;

    @NotNull(message = "수량은 필수입니다.")
    private Long amount;
}
