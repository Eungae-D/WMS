package com.wms.domain.inputWarehouseDetail.service.impl;

import com.wms.domain.area.entity.Area;
import com.wms.domain.area.repository.AreaRepository;
import com.wms.domain.cell.entity.Cell;
import com.wms.domain.cell.repository.CellRepository;
import com.wms.domain.inputWarehouse.entity.InputWarehouse;
import com.wms.domain.inputWarehouseDetail.dto.request.InputWarehouseDetailCompleteDTO;
import com.wms.domain.inputWarehouseDetail.dto.request.InputWarehouseDetailRequestDTO;
import com.wms.domain.inputWarehouseDetail.dto.response.InputWarehouseDetailsResponseDTO;
import com.wms.domain.inputWarehouseDetail.dto.response.InputWarehouseDetailsResponseDTO2;
import com.wms.domain.inputWarehouseDetail.entity.InputWarehouseDetail;
import com.wms.domain.inputWarehouseDetail.repository.InputWarehouseDetailRepository;
import com.wms.domain.inputWarehouseDetail.service.InputWarehouseDetailService;
import com.wms.domain.inventory.service.InventoryService;
import com.wms.domain.item.entity.Item;
import com.wms.domain.item.repository.ItemRepository;
import com.wms.domain.lot.entity.Lot;
import com.wms.domain.lot.repository.LotRepository;
import com.wms.domain.purchaseDetail.entity.PurchaseDetail;
import com.wms.domain.purchaseDetail.repository.PurchaseDetailRepository;
import com.wms.domain.purchaseDetail.service.PurchaseDetailService;
import com.wms.domain.purchaseSheet.entity.PurchaseSheet;
import com.wms.domain.purchaseSheet.service.PurchaseSheetService;
import com.wms.domain.rack.entity.Rack;
import com.wms.domain.rack.repository.RackRepository;
import com.wms.domain.warehouse.entity.Warehouse;
import com.wms.domain.warehouse.repository.WarehouseRepository;
import com.wms.global.exception.exception.*;
import com.wms.global.exception.responseCode.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InputWarehouseDetailServiceImpl implements InputWarehouseDetailService {

    private final InputWarehouseDetailRepository inputWarehouseDetailRepository;
    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;
    private final LotRepository lotRepository;
    private final PurchaseDetailRepository purchaseDetailRepository;
    private final AreaRepository areaRepository;
    private final RackRepository rackRepository;
    private final CellRepository cellRepository;
    private final InventoryService inventoryService;
    private final PurchaseDetailService purchaseDetailService;
    private final PurchaseSheetService purchaseSheetService;

    @Override
    @Transactional
    public void createInputWarehouseDetails(InputWarehouse inputWarehouse, List<InputWarehouseDetailRequestDTO> inputWarehouseDetailRequestDTOs) {
        for (InputWarehouseDetailRequestDTO detailRequest : inputWarehouseDetailRequestDTOs) {
            Item item = itemRepository.findById(detailRequest.getItemId())
                    .orElseThrow(() -> new ItemException(ItemExceptionResponseCode.ITEM_NOT_FOUND, "품목을 찾을 수 없습니다."));
            Warehouse warehouse = warehouseRepository.findById(detailRequest.getWarehouseId())
                    .orElseThrow(() -> new WarehouseException(WarehouseExceptionResponseCode.WAREHOUSE_NOT_FOUND, "창고를 찾을 수 없습니다."));
            Lot lot = lotRepository.findById(detailRequest.getLotId())
                    .orElseThrow(() -> new LotException(LotExceptionResponseCode.LOT_NOT_FOUND, "로트를 찾을 수 없습니다."));
            PurchaseDetail purchaseDetail = purchaseDetailRepository.findById(detailRequest.getPurchaseDetailId())
                    .orElseThrow(() -> new PurchaseDetailException(PurchaseDetailExceptionResponseCode.PURCHASE_DETAIL_NOT_FOUND, "발주 상세를 찾을 수 없습니다."));
            Area area = areaRepository.findById(detailRequest.getAreaId())
                    .orElseThrow(() -> new AreaException(AreaExceptionResponseCode.AREA_NOT_FOUND, "구역을 찾을 수 없습니다."));
            Rack rack = rackRepository.findById(detailRequest.getRackId())
                    .orElseThrow(() -> new RackException(RackExceptionResponseCode.RACK_NOT_FOUND, "랙을 찾을 수 없습니다."));
            Cell cell = cellRepository.findById(detailRequest.getCellId())
                    .orElseThrow(() -> new CellException(CellExceptionResponseCode.CELL_NOT_FOUND, "셀을 찾을 수 없습니다."));

            // 입고 상세 생성
            InputWarehouseDetail inputWarehouseDetail = detailRequest.toEntity(inputWarehouse,item,warehouse,lot,purchaseDetail,area,rack,cell);
            inputWarehouseDetailRepository.save(inputWarehouseDetail);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<InputWarehouseDetailsResponseDTO> getInputWarehouseDetails(Long inputWarehouseId) {

        // 입고서 상세 정보 조회
        List<InputWarehouseDetail> inputWarehouseDetails = inputWarehouseDetailRepository.findAllByInputWarehouseId(inputWarehouseId);

        if (inputWarehouseDetails.isEmpty()) {
            throw new InputWarehouseDetailException(InputWarehouseDetailExceptionResponseCode.INPUT_WAREHOUSE_DETAIL_NOT_FOUND, "입고서 상세 정보를 찾을 수 없습니다.");
        }

        return inputWarehouseDetails.stream()
                .map(InputWarehouseDetailsResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<InputWarehouseDetailsResponseDTO2> getInputWarehouseDetailsByPurchaseSheetId(Long purchaseSheetId) {
        // 발주서 ID에 해당하는 입고 상세 정보 조회
        List<InputWarehouseDetail> inputWarehouseDetails = inputWarehouseDetailRepository.findAllByPurchaseSheetId(purchaseSheetId);

        if (inputWarehouseDetails.isEmpty()) {
            throw new InputWarehouseDetailException(InputWarehouseDetailExceptionResponseCode.INPUT_WAREHOUSE_DETAIL_NOT_FOUND, "발주서에 대한 입고 상세 정보를 찾을 수 없습니다.");
        }

        return inputWarehouseDetails.stream()
                .map(InputWarehouseDetailsResponseDTO2::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void completeInputWarehouse(List<InputWarehouseDetailCompleteDTO> inputWarehouseDetailIds) {
        // 1. DTO에서 ID 리스트 추출
        List<Long> ids = inputWarehouseDetailIds.stream()
                .map(InputWarehouseDetailCompleteDTO::getInputWarehouseDetailId)
                .collect(Collectors.toList());

        // 2. 해당 입고 상세 정보 조회
        List<InputWarehouseDetail> inputWarehouseDetails = inputWarehouseDetailRepository.findAllById(ids);

        if (inputWarehouseDetails.isEmpty()) {
            throw new InputWarehouseDetailException(InputWarehouseDetailExceptionResponseCode.INPUT_WAREHOUSE_DETAIL_NOT_FOUND, "입고 상세 정보를 찾을 수 없습니다.");
        }

        // 2. 재고 업데이트
        for (InputWarehouseDetail detail : inputWarehouseDetails) {
            inventoryService.updateInventory(detail);

            // 발주 상세 상태 변경
            purchaseDetailService.completePurchaseDetail(detail.getPurchaseDetail().getId());
        }

        // 3. 발주서 상태 업데이트
        PurchaseSheet purchaseSheet = inputWarehouseDetails.get(0).getInputWarehouse()
                .getPurchaseSheet();

        if (purchaseSheet == null) {
            throw new InputWarehouseException(
                    InputWarehouseExceptionResponseCode.INPUT_WAREHOUSE_NOT_FOUND, "입고 상세 정보에 해당하는 발주서를 찾을 수 없습니다."
            );
        }

        purchaseSheetService.updatePurchaseSheetStatus(purchaseSheet);
    }
}
