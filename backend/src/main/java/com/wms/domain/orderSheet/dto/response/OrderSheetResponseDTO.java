package com.wms.domain.orderSheet.dto.response;

import com.wms.domain.orderSheet.entity.OrderSheet;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Builder
public class OrderSheetResponseDTO {

    private Long orderSheetId;
    private String userName;       // 담당자 이름
    private String clientName;     // 거래처 이름
    private String status;         // 진행 상태
    private LocalDate dueDate;     // 납기일자
    private Long totalAmount;      // 주문 금액 합계
    private LocalDateTime createdDate; // 수주서 작성일자
    private List<String> itemNames;    // 품목명 리스트

    public static OrderSheetResponseDTO fromEntity(OrderSheet orderSheet) {
        Long totalAmount = orderSheet.getOrderDetails().stream()
                .mapToLong(detail -> detail.getItem().getShippingPrice() * detail.getAmount())
                .sum();

        List<String> itemNames = orderSheet.getOrderDetails().stream()
                .map(detail -> detail.getItem().getName())
                .collect(Collectors.toList());

        return OrderSheetResponseDTO.builder()
                .orderSheetId(orderSheet.getId())
                .userName(orderSheet.getUser().getName())
                .clientName(orderSheet.getClient().getName())
                .status(orderSheet.getStatus().name())
                .dueDate(orderSheet.getDueDate())
                .totalAmount(totalAmount)
                .createdDate(orderSheet.getCreatedDate()) // 작성일자 추가
                .itemNames(itemNames)                    // 품목명 리스트 추가
                .build();
    }
}
