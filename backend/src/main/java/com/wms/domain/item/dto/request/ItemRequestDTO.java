package com.wms.domain.item.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ItemRequestDTO {
    private String code;
    private String name;
    private Long unitPrice;
    private Long shippingPrice;
    private MultipartFile itemImage;
    private Long clientId;

    public ItemRequestDTO withItemImage(MultipartFile itemImage){
        return ItemRequestDTO.builder()
                .code(this.code)
                .name(this.name)
                .unitPrice(this.unitPrice)
                .shippingPrice(this.shippingPrice)
                .itemImage(itemImage)
                .clientId(this.clientId)
                .build();
    }
}
