package com.wms.domain.inventory.service;

import com.wms.domain.inventory.dto.request.InventoryRequestDTO;
import com.wms.domain.inventory.dto.response.InventoryResponseDTO;

import java.util.List;

public interface InventoryService {
//    void registerInventory(InventoryRequestDTO inventoryRequestDTO);

    // 재고등록(목록)
    void registerInventoryList(List<InventoryRequestDTO> inventoryRequestDTOList);

    // 전체 재고 목록 조회
    List<InventoryResponseDTO> getAllInventory();
}
