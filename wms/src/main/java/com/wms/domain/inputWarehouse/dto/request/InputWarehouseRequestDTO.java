package com.wms.domain.inputWarehouse.dto.request;

import com.wms.domain.inputWarehouse.entity.InputWarehouse;
import com.wms.domain.inputWarehouse.entity.Status;
import com.wms.domain.inputWarehouseDetail.dto.request.InputWarehouseDetailRequestDTO;
import com.wms.domain.purchaseSheet.entity.PurchaseSheet;
import com.wms.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InputWarehouseRequestDTO {
    @NotNull(message = "발주서 ID는 필수입니다.")
    private Long purchaseSheetId;

    @NotNull(message = "입고 상세 정보가 필요합니다.")
    private List<InputWarehouseDetailRequestDTO> inputWarehouseDetails;

    // InputWarehouse 엔티티로 변환하는 메서드
    public InputWarehouse toEntity(User user, PurchaseSheet purchaseSheet) {
        return InputWarehouse.builder()
                .status(Status.PURCHASE)
                .user(user)
                .purchaseSheet(purchaseSheet)
                .build();
    }
}
