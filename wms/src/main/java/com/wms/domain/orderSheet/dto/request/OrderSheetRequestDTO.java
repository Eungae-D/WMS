package com.wms.domain.orderSheet.dto.request;

import com.wms.domain.client.entity.Client;
import com.wms.domain.orderDetail.dto.request.OrderDetailRequestDTO;
import com.wms.domain.orderSheet.entity.OrderSheet;
import com.wms.domain.orderSheet.entity.Status;
import com.wms.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderSheetRequestDTO {

    private LocalDate dueDate;
    private Long clientId;
    private List<OrderDetailRequestDTO> orderDetails;

    public OrderSheet toEntity(User user, Client client) {
        return OrderSheet.builder()
                .status(Status.RECEIVED)  // 상태를 기본값으로 'RECEIVED'로 설정
                .dueDate(this.dueDate)
                .user(user)
                .client(client)
                .build();
    }
}
