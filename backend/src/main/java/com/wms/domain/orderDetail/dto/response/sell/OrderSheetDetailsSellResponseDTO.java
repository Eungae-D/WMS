package com.wms.domain.orderDetail.dto.response.sell;

import com.wms.domain.orderSheet.entity.OrderSheet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class OrderSheetDetailsSellResponseDTO {
    private String userName;
    private String departmentName;
    private LocalDate dueDate;
    private String orderClientName;
    private String orderClientCode;
    private List<OrderDetailSellResponseDTO> orderDetails;

    public static OrderSheetDetailsSellResponseDTO fromEntity(OrderSheet orderSheet, List<OrderDetailSellResponseDTO> orderDetails) {
        return OrderSheetDetailsSellResponseDTO.builder()
                .userName(orderSheet.getUser().getName())
                .departmentName(orderSheet.getUser().getDepartment().getDepartment_name())
                .dueDate(orderSheet.getDueDate())
                .orderClientName(orderSheet.getClient().getName())
                .orderClientCode(orderSheet.getClient().getCode())
                .orderDetails(orderDetails)
                .build();
    }
}
