package com.wms.domain.orderDetail.dto.response;

import com.wms.domain.inventory.entity.Inventory;
import com.wms.domain.item.entity.Item;
import com.wms.domain.orderDetail.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class OrderDetailResponseDTO {

    private Long id;
    private String itemCode;         // 품목 코드
    private String itemName;         // 품목명
    private String itemClientName;   // 품목의 거래처 이름
    private String itemClientCode;   // 품목의 거래처 코드
    private Long orderQuantity;      // 주문 수량
    private Long inventoryQuantity;  // 재고 수량
    private Long shortageQuantity;   // 부족 수량 (주문 수량 - 재고 수량)
    private Long shippingPrice;      // 판매 단가 (shippingPrice)
    private Long unitPrice;          // 구매 단가 (unitPrice)
    private Long totalAmount;        // 합계 (판매 단가 * 주문 수량)
    private String userName;         // 담당자 이름
    private String departmentName;   // 담당자 부서명
    private LocalDate dueDate;       // 납기일자
    private String orderClientName;  // 수주서의 거래처 이름
    private String orderClientCode;  // 수주서의 거래처 코드

    // 엔티티에서 DTO로 변환하는 메서드
    public static OrderDetailResponseDTO fromEntity(OrderDetail orderDetail) {
        Item item = orderDetail.getItem();
        Long orderQuantity = orderDetail.getAmount();
        Long inventoryQuantity = item.getInventory().stream().mapToLong(Inventory::getQuantity).sum();
        Long shortageQuantity = orderQuantity - inventoryQuantity;

        return OrderDetailResponseDTO.builder()
                .id(orderDetail.getId())
                .itemCode(item.getCode())
                .itemName(item.getName())
                .itemClientName(item.getClient().getName())  // 품목의 거래처 이름
                .itemClientCode(item.getClient().getCode())  // 품목의 거래처 코드
                .orderQuantity(orderQuantity)
                .inventoryQuantity(inventoryQuantity)
                .shortageQuantity(shortageQuantity)
                .shippingPrice(item.getShippingPrice())
                .unitPrice(item.getUnitPrice())  // 구매 단가
                .totalAmount(item.getShippingPrice() * orderQuantity)
                .userName(orderDetail.getOrderSheet().getUser().getName())  // 담당자 이름
                .departmentName(orderDetail.getOrderSheet().getUser().getDepartment().getDepartment_name())  // 담당자 부서명
                .dueDate(orderDetail.getOrderSheet().getDueDate())  // 납기일자
                .orderClientName(orderDetail.getOrderSheet().getClient().getName())  // 수주서의 거래처 이름
                .orderClientCode(orderDetail.getOrderSheet().getClient().getCode())  // 수주서의 거래처 코드
                .build();
    }
}
