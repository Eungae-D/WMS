package com.wms.domain.purchaseDetail.dto.response;

import com.wms.domain.purchaseSheet.entity.PurchaseSheet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PurchaseSheetDetailsResponseDTO {
    private String userName;         // 담당자 이름
    private String departmentName;   // 담당자 부서명
    private LocalDate dueDate;       // 납기일자
    private String clientName;       // 발주서의 거래처 이름
    private String clientCode;       // 발주서의 거래처 코드
    private List<PurchaseDetailResponseDTO> purchaseDetails;  // 품목 리스트

    public static PurchaseSheetDetailsResponseDTO fromEntity(PurchaseSheet purchaseSheet, List<PurchaseDetailResponseDTO> purchaseDetails) {
        return PurchaseSheetDetailsResponseDTO.builder()
                .userName(purchaseSheet.getUser().getName())
                .departmentName(purchaseSheet.getUser().getDepartment().getDepartment_name())
                .dueDate(purchaseSheet.getDueDate())
                .clientName(purchaseSheet.getClient().getName())
                .clientCode(purchaseSheet.getClient().getCode())
                .purchaseDetails(purchaseDetails)
                .build();
    }
}
