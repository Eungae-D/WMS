package com.wms.domain.inventory.service;

import com.wms.domain.inputWarehouseDetail.entity.InputWarehouseDetail;
import com.wms.domain.inventory.dto.request.InventoryRequestDTO;
import com.wms.domain.inventory.dto.response.InventoryResponseDTO;

import java.util.List;

public interface InventoryService {

    // 재고등록(목록)
    void registerInventoryList(List<InventoryRequestDTO> inventoryRequestDTOList);

    // 전체 재고 목록 조회
    List<InventoryResponseDTO> getAllInventory();

    // 창고 ID로 필터링된 재고 목록 조회
    List<InventoryResponseDTO> getInventoryByWarehouseId(Long warehouseId);

    // 창고 및 구역 ID로 필터링된 재고 목록 조회
    List<InventoryResponseDTO> getInventoryByWarehouseIdAndAreaId(Long warehouseId, Long areaId);

    // 창고, 구역, 랙 ID로 필터링된 재고 목록 조회
    List<InventoryResponseDTO> getInventoryByWarehouseIdAndAreaIdAndRackId(Long warehouseId, Long areaId, Long rackId);

    // 창고, 구역, 랙, 셀 ID로 필터링된 재고 목록 조회
    List<InventoryResponseDTO> getInventoryByWarehouseIdAndAreaIdAndRackIdAndCellId(Long warehouseId, Long areaId, Long rackId, Long cellId);

    // 인벤토리 업데이트
    void updateInventory(InputWarehouseDetail detail);
}
