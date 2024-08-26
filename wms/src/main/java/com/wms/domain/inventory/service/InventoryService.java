package com.wms.domain.inventory.service;

import com.wms.domain.inventory.dto.request.InventoryRequestDTO;

public interface InventoryService {
    void registerInventory(InventoryRequestDTO inventoryRequestDTO);
}
