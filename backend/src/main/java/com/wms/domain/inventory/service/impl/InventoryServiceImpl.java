package com.wms.domain.inventory.service.impl;

import com.wms.domain.area.entity.Area;
import com.wms.domain.area.repository.AreaRepository;
import com.wms.domain.cell.entity.Cell;
import com.wms.domain.cell.repository.CellRepository;
import com.wms.domain.inputWarehouseDetail.entity.InputWarehouseDetail;
import com.wms.domain.inventory.dto.request.InventoryRequestDTO;
import com.wms.domain.inventory.dto.response.InventoryResponseDTO;
import com.wms.domain.inventory.entity.Inventory;
import com.wms.domain.inventory.repository.InventoryRepository;
import com.wms.domain.inventory.service.InventoryService;
import com.wms.domain.item.entity.Item;
import com.wms.domain.item.repository.ItemRepository;
import com.wms.domain.lot.entity.Lot;
import com.wms.domain.lot.repository.LotRepository;
import com.wms.domain.lot.service.LotService;
import com.wms.domain.rack.entity.Rack;
import com.wms.domain.rack.repository.RackRepository;
import com.wms.domain.user.entity.User;
import com.wms.domain.user.repository.UserRepository;
import com.wms.domain.warehouse.entity.Warehouse;
import com.wms.domain.warehouse.repository.WarehouseRepository;
import com.wms.global.exception.exception.*;
import com.wms.global.exception.responseCode.*;
import com.wms.global.util.service.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final UserFindService userFindService;
    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;
    private final AreaRepository areaRepository;
    private final RackRepository rackRepository;
    private final CellRepository cellRepository;
    private final LotService lotService;


    // 재고 등록(목록)
    @Override
    @Transactional
    public void registerInventoryList(List<InventoryRequestDTO> inventoryRequestDTOList) {
        User user = userFindService.getCurrentUser();

        for(InventoryRequestDTO inventoryRequestDTO: inventoryRequestDTOList){
            Item item = itemRepository.findById(inventoryRequestDTO.getItemId())
                    .orElseThrow(() -> new ItemException(ItemExceptionResponseCode.ITEM_NOT_FOUND, "아이템을 찾을 수 없습니다."));

            Warehouse warehouse = warehouseRepository.findById(inventoryRequestDTO.getWarehouseId())
                    .orElseThrow(() -> new WarehouseException(WarehouseExceptionResponseCode.WAREHOUSE_NOT_FOUND, "창고를 찾을 수 없습니다."));

            Area area = areaRepository.findById(inventoryRequestDTO.getAreaId())
                    .orElseThrow(() -> new AreaException(AreaExceptionResponseCode.AREA_NOT_FOUND, "구역을 찾을 수 없습니다."));

            Rack rack = rackRepository.findById(inventoryRequestDTO.getRackId())
                    .orElseThrow(() -> new RackException(RackExceptionResponseCode.RACK_NOT_FOUND, "랙을 찾을 수 없습니다."));

            Cell cell = cellRepository.findById(inventoryRequestDTO.getCellId())
                    .orElseThrow(() -> new CellException(CellExceptionResponseCode.CELL_NOT_FOUND, "셀을 찾을 수 없습니다."));

            // 새로운 로트 생성
            Lot lot = lotService.createLot(item);

            Inventory inventory = Inventory.builder()
                    .user(user)
                    .item(item)
                    .warehouse(warehouse)
                    .area(area)
                    .rack(rack)
                    .cell(cell)
                    .lot(lot)
                    .quantity(inventoryRequestDTO.getQuantity())
                    .orderQuantity(0)
                    .build();

            inventoryRepository.save(inventory);
        }
    }


    // 전체 재고 목록 조회
    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> getAllInventory() {
        List<Inventory> inventories = inventoryRepository.findAllWithDetails();

        if (inventories.isEmpty()) {
            throw new InventoryException(InventoryExceptionResponseCode.INVENTORY_NOT_FOUND, "재고가 없습니다.");
        }

        return inventories.stream()
                .map(InventoryResponseDTO::fromEntity)  // fromEntity 메서드 활용
                .collect(Collectors.toList());
    }

    // 창고에 속한 재고 목록 조회
    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> getInventoryByWarehouseId(Long warehouseId) {
        List<Inventory> inventories = inventoryRepository.findAllByWarehouseId(warehouseId);

        if (inventories.isEmpty()) {
            throw new InventoryException(InventoryExceptionResponseCode.INVENTORY_NOT_FOUND, "해당 창고에 재고가 없습니다.");
        }

        return inventories.stream()
                .map(InventoryResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 창고, 구역에 속한 재고 목록 조회
    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> getInventoryByWarehouseIdAndAreaId(Long warehouseId, Long areaId) {
        List<Inventory> inventories = inventoryRepository.findAllByWarehouseIdAndAreaId(warehouseId, areaId);

        if (inventories.isEmpty()) {
            throw new InventoryException(InventoryExceptionResponseCode.INVENTORY_NOT_FOUND, "해당 창고와 구역에 재고가 없습니다.");
        }

        return inventories.stream()
                .map(InventoryResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 창고, 구역, 랙에 속한 재고 목록 조회
    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> getInventoryByWarehouseIdAndAreaIdAndRackId(Long warehouseId, Long areaId, Long rackId) {
        List<Inventory> inventories = inventoryRepository.findAllByWarehouseIdAndAreaIdAndRackId(warehouseId, areaId, rackId);

        if (inventories.isEmpty()) {
            throw new InventoryException(InventoryExceptionResponseCode.INVENTORY_NOT_FOUND, "해당 창고, 구역, 랙에 재고가 없습니다.");
        }

        return inventories.stream()
                .map(InventoryResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 창고, 구역, 랙, 셀에 속한 재고 목록 조회
    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> getInventoryByWarehouseIdAndAreaIdAndRackIdAndCellId(Long warehouseId, Long areaId, Long rackId, Long cellId) {
        List<Inventory> inventories = inventoryRepository.findAllByWarehouseIdAndAreaIdAndRackIdAndCellId(warehouseId, areaId, rackId, cellId);

        if (inventories.isEmpty()) {
            throw new InventoryException(InventoryExceptionResponseCode.INVENTORY_NOT_FOUND, "해당 창고, 구역, 랙, 셀에 재고가 없습니다.");
        }

        return inventories.stream()
                .map(InventoryResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateInventory(InputWarehouseDetail detail) {
        // 1. 기존 재고 확인
        Optional<Inventory> optionalInventory = inventoryRepository.findByItemAndWarehouseAndAreaAndRackAndCellAndLot(
                detail.getItem().getId(),
                detail.getWarehouse().getId(),
                detail.getArea().getId(),
                detail.getRack().getId(),
                detail.getCell().getId(),
                detail.getLot().getId()
        );

        // 2. 기존 재고가 있으면 업데이트
        if (optionalInventory.isPresent()) {
            Inventory inventory = optionalInventory.get();
            inventory.addQuantity(detail.getAmount());
            inventoryRepository.save(inventory);
        } else {
            // 3. 기존 재고가 없으면 새로 생성
            Inventory newInventory = Inventory.builder()
                    .quantity(detail.getAmount().intValue()) // 새로운 수량 설정
                    .orderQuantity(0) // 초기 발주량 설정
                    .user(detail.getInputWarehouse().getUser()) // 입고한 사용자 정보 설정
                    .item(detail.getItem()) // 품목 정보 설정
                    .warehouse(detail.getWarehouse()) // 창고 정보 설정
                    .area(detail.getArea()) // 구역 정보 설정
                    .rack(detail.getRack()) // 랙 정보 설정
                    .cell(detail.getCell()) // 셀 정보 설정
                    .lot(detail.getLot()) // 로트 정보 설정
                    .build();
            inventoryRepository.save(newInventory);
        }
    }
}
