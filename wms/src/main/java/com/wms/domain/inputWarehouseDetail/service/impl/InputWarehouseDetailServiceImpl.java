package com.wms.domain.inputWarehouseDetail.service.impl;

import com.wms.domain.area.entity.Area;
import com.wms.domain.area.repository.AreaRepository;
import com.wms.domain.cell.entity.Cell;
import com.wms.domain.cell.repository.CellRepository;
import com.wms.domain.inputWarehouse.entity.InputWarehouse;
import com.wms.domain.inputWarehouseDetail.dto.request.InputWarehouseDetailRequestDTO;
import com.wms.domain.inputWarehouseDetail.entity.InputWarehouseDetail;
import com.wms.domain.inputWarehouseDetail.repository.InputWarehouseDetailRepository;
import com.wms.domain.inputWarehouseDetail.service.InputWarehouseDetailService;
import com.wms.domain.item.entity.Item;
import com.wms.domain.item.repository.ItemRepository;
import com.wms.domain.lot.entity.Lot;
import com.wms.domain.lot.repository.LotRepository;
import com.wms.domain.purchaseDetail.entity.PurchaseDetail;
import com.wms.domain.purchaseDetail.repository.PurchaseDetailRepository;
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
}
