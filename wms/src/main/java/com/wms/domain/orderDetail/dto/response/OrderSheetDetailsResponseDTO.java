package com.wms.domain.orderDetail.dto.response;

import com.wms.domain.orderSheet.entity.OrderSheet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class OrderSheetDetailResponseDTO {

    private String userName;         // 담당자 이름
    private String departmentName;   // 담당자 부서명
    private LocalDate dueDate;       // 납기일자
    private String orderClientName;  // 수주서의 거래처 이름
    private String orderClientCode;  // 수주서의 거래처 코드
    private List<OrderDetailResponseDTO> orderDetails;  // 품목 리스트

    public static OrderSheetDetailsResponseDTO fromEntity(OrderSheet orderSheet, List<OrderDetailResponseDTO> orderDetails) {
        return OrderSheetDetailsResponseDTO.builder()
                .userName(orderSheet.getUser().getName())
                .departmentName(orderSheet.getUser().getDepartment().getDepartment_name())
                .dueDate(orderSheet.getDueDate())
                .orderClientName(orderSheet.getClient().getName())
                .orderClientCode(orderSheet.getClient().getCode())
                .orderDetails(orderDetails)
                .build();
    }
}
