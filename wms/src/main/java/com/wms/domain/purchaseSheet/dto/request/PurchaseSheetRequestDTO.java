package com.wms.domain.purchaseSheet.dto.request;

import com.wms.domain.client.entity.Client;
import com.wms.domain.orderSheet.entity.OrderSheet;
import com.wms.domain.purchaseDetail.dto.request.PurchaseDetailRequestDTO;
import com.wms.domain.purchaseSheet.entity.PurchaseSheet;
import com.wms.domain.purchaseSheet.entity.Status;
import com.wms.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PurchaseSheetRequestDTO {

    @NotNull(message = "납기일자가 필요합니다.")
    private LocalDate dueDate;

    @NotNull(message = "수주서 ID가 필요합니다.")
    private Long orderSheetId;

    @NotNull(message = "거래처 ID가 필요합니다.")
    private Long clientId;

    @NotNull(message = "발주 상세 정보가 필요합니다.")
    private List<PurchaseDetailRequestDTO> purchaseDetails;

    // PurchaseSheet 엔티티로 변환하는 메서드
    public PurchaseSheet toEntity(OrderSheet orderSheet, User user, Client client) {
        return PurchaseSheet.builder()
                .status(Status.ORDERING)  // 발주서 상태는 ORDERING으로 기본 설정
                .dueDate(dueDate)
                .orderSheet(orderSheet)
                .user(user)
                .client(client)
                .build();
    }
}
