package com.wms.domain.client.dto.response;

import com.wms.domain.client.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ClientResponseDTO {
    private Long id;
    private String category;
    private String name;
    private String owner;
    private String tel;
    private String address1;

    public static ClientResponseDTO fromEntity(Client client) {
        return ClientResponseDTO.builder()
                .id(client.getId())
                .category(client.getCategory().name())
                .name(client.getName())
                .owner(client.getOwner())
                .tel(client.getTel())
                .address1(client.getAddress1())
                .build();
    }
}
