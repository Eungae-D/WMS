package com.wms.domain.inventory.dto.response;


import com.wms.domain.inventory.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class InventoryResponseDTO {
    private Long id;
    private String itemName;
    private String warehouseName;
    private String areaName;
    private String rackName;
    private String cellName;
    private int quantity;
    private String lotNumber;

    public static InventoryResponseDTO fromEntity(Inventory inventory) {
        return InventoryResponseDTO.builder()
                .id(inventory.getId())
                .itemName(inventory.getItem().getName())
                .warehouseName(inventory.getWarehouse().getName())
                .areaName(inventory.getArea().getName())
                .rackName(inventory.getRack().getName())
                .cellName(inventory.getCell().getName())
                .quantity(inventory.getQuantity())
                .lotNumber(inventory.getLot().getLotNumber())
                .build();
    }
}
