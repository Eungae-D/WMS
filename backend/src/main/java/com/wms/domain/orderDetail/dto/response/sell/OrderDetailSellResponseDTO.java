package com.wms.domain.orderDetail.dto.response.sell;

import com.wms.domain.orderDetail.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class OrderDetailSellResponseDTO {
    private Long id;
    private Long itemId;
    private String itemCode;
    private String itemName;
    private Long itemClientId;
    private String itemClientName;
    private String itemClientCode;
    private Long orderQuantity;
    private Long inventoryQuantity;
    private Long shortageQuantity;
    private Long shippingPrice;
    private Long unitPrice;
    private Long totalAmount;
    private List<OrderDetailSellDTO> sellDetails;

    public static OrderDetailSellResponseDTO fromEntity(OrderDetail orderDetail, List<OrderDetailSellDTO> sellDetails) {
        Long orderQuantity = orderDetail.getAmount();
        Long inventoryQuantity = orderDetail.getItem().getInventory().stream()
                .mapToLong(inventory -> inventory.getQuantity())
                .sum();
        Long shortageQuantity = orderQuantity - inventoryQuantity;

        return OrderDetailSellResponseDTO.builder()
                .id(orderDetail.getId())
                .itemId(orderDetail.getItem().getId())
                .itemCode(orderDetail.getItem().getCode())
                .itemName(orderDetail.getItem().getName())
                .itemClientId(orderDetail.getItem().getClient().getId())
                .itemClientName(orderDetail.getItem().getClient().getName())
                .itemClientCode(orderDetail.getItem().getClient().getCode())
                .orderQuantity(orderQuantity)
                .inventoryQuantity(inventoryQuantity)
                .shortageQuantity(shortageQuantity)
                .shippingPrice(orderDetail.getItem().getShippingPrice())
                .unitPrice(orderDetail.getItem().getUnitPrice())
                .totalAmount(orderQuantity * orderDetail.getItem().getShippingPrice())
                .sellDetails(sellDetails)
                .build();
    }
}
