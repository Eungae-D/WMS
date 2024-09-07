package com.wms.domain.purchaseSheet.dto.response;

import com.wms.domain.purchaseSheet.entity.PurchaseSheet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Builder
public class PurchaseSheetResponseDTO {
    private Long purchaseSheetId;
    private String userName;       // 담당자 이름
    private String clientName;     // 거래처 이름
    private String status;         // 진행 상태
    private LocalDate dueDate;     // 납기일자
    private Long totalAmount;      // 주문 금액 합계
    private LocalDateTime createdDate; // 발주서 작성일자
    private List<String> itemNames;    // 품목명 리스트

    public static PurchaseSheetResponseDTO fromEntity(PurchaseSheet purchaseSheet) {
        Long totalAmount = purchaseSheet.getPurchaseDetails().stream()
                .mapToLong(detail -> detail.getItem().getUnitPrice() * detail.getAmount())
                .sum();

        List<String> itemNames = purchaseSheet.getPurchaseDetails().stream()
                .map(detail -> detail.getItem().getName())
                .collect(Collectors.toList());

        return PurchaseSheetResponseDTO.builder()
                .purchaseSheetId(purchaseSheet.getId())
                .userName(purchaseSheet.getUser().getName())
                .clientName(purchaseSheet.getClient().getName())
                .status(purchaseSheet.getStatus().name())
                .dueDate(purchaseSheet.getDueDate())
                .totalAmount(totalAmount)
                .createdDate(purchaseSheet.getCreatedDate())
                .itemNames(itemNames)
                .build();
    }
}
