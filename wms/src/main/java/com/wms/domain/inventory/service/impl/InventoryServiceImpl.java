package com.wms.domain.inventory.service.impl;

import com.wms.domain.area.entity.Area;
import com.wms.domain.area.repository.AreaRepository;
import com.wms.domain.cell.entity.Cell;
import com.wms.domain.cell.repository.CellRepository;
import com.wms.domain.inventory.dto.request.InventoryRequestDTO;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;
    private final AreaRepository areaRepository;
    private final RackRepository rackRepository;
    private final CellRepository cellRepository;
    private final LotService lotService;


    @Override
    @Transactional
    public void registerInventory(InventoryRequestDTO inventoryRequestDTO) {
        User user = userRepository.findById(inventoryRequestDTO.getUserId())
                .orElseThrow(() -> new UserException(UserExceptionResponseCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다."));

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
                .orderQuantity(inventoryRequestDTO.getOrderQuantity())
                .build();

        inventoryRepository.save(inventory);
    }
}
