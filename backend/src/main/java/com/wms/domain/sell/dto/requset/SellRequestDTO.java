package com.wms.domain.sell.dto.requset;


import com.wms.domain.orderSheet.entity.OrderSheet;
import com.wms.domain.sell.entity.Sell;
import com.wms.domain.user.entity.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SellRequestDTO {
    private LocalDate dueDate;
    private String status;

//    public Sell toEntity(User user, OrderSheet orderSheet) {
//        return Sell.builder()
//                .user(user)
//                .orderSheet(orderSheet)
//                .dueDate(this.dueDate)
//                .status(Status.valueOf(this.status.toUpperCase()))
//                .build();
//    }
}
