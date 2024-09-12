package com.wms.domain.orderDetail.dto.request;

import com.wms.domain.item.entity.Item;
import com.wms.domain.orderDetail.entity.OrderDetail;
import com.wms.domain.orderSheet.entity.OrderSheet;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderDetailRequestDTO {

    private Long itemId;
    private Long amount;

    public OrderDetail toEntity(OrderSheet orderSheet, Item item) {
        return OrderDetail.builder()
                .amount(this.amount)
                .orderSheet(orderSheet)
                .item(item)
                .build();
    }

}
