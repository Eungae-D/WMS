package com.wms.domain.orderDetail.dto.response;

import com.wms.domain.inventory.entity.Inventory;
import com.wms.domain.item.entity.Item;
import com.wms.domain.orderDetail.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrderDetailResponseDTO {

    private Long id;
    private String itemCode;         // 품목 코드
    private String itemName;         // 품목명
    private String clientName;       // 거래처 이름
    private Long orderQuantity;       // 주문 수량
    private Long inventoryQuantity;   // 재고 수량
    private Long shortageQuantity;    // 부족 수량 (주문 수량 - 재고 수량)
    private Long shippingPrice;      // 판매 단가 (shippingPrice)
    private Long totalAmount;        // 합계 (판매 단가 * 주문 수량)

    public static OrderDetailResponseDTO fromEntity(OrderDetail orderDetail) {
        Item item = orderDetail.getItem();
        Long orderQuantity = orderDetail.getAmount();
        Long inventoryQuantity = item.getInventory().stream().mapToLong(Inventory::getQuantity).sum();
        Long shortageQuantity = orderQuantity - inventoryQuantity;

        return OrderDetailResponseDTO.builder()
                .id(orderDetail.getId())
                .itemCode(item.getCode())
                .itemName(item.getName())
                .clientName(item.getClient().getName())
                .orderQuantity(orderQuantity)
                .inventoryQuantity(inventoryQuantity)
                .shortageQuantity(shortageQuantity)
                .shippingPrice(item.getShippingPrice())
                .totalAmount(item.getShippingPrice() * orderQuantity)
                .build();
    }
}
