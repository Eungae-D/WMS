package com.wms.domain.item.dto.response;

import com.wms.domain.item.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ItemResponseDTO {
    private Long id;
    private String code;
    private String name;
    private Long unitPrice;
    private Long shippingPrice;
    private String itemImage;
    private String clientName;

    public static ItemResponseDTO fromEntity(Item item) {
        return ItemResponseDTO.builder()
                .id(item.getId())
                .code(item.getCode())
                .name(item.getName())
                .unitPrice(item.getUnitPrice())
                .shippingPrice(item.getShippingPrice())
                .itemImage(item.getItemImage())
                .clientName(item.getClient().getName())
                .build();
    }
}
