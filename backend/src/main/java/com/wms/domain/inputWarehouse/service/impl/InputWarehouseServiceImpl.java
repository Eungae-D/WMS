package com.wms.domain.inputWarehouse.service.impl;

import com.wms.domain.inputWarehouse.dto.request.InputWarehouseRequestDTO;
import com.wms.domain.inputWarehouse.dto.response.InputWarehouseResponseDTO;
import com.wms.domain.inputWarehouse.entity.InputWarehouse;
import com.wms.domain.inputWarehouse.repository.InputWarehouseRepository;
import com.wms.domain.inputWarehouse.service.InputWarehouseService;
import com.wms.domain.inputWarehouseDetail.entity.InputWarehouseDetail;
import com.wms.domain.inputWarehouseDetail.repository.InputWarehouseDetailRepository;
import com.wms.domain.inputWarehouseDetail.service.InputWarehouseDetailService;
import com.wms.domain.inventory.service.InventoryService;
import com.wms.domain.purchaseDetail.service.PurchaseDetailService;
import com.wms.domain.purchaseSheet.entity.PurchaseSheet;
import com.wms.domain.purchaseSheet.repository.PurchaseSheetRepository;
import com.wms.domain.purchaseSheet.service.PurchaseSheetService;
import com.wms.domain.user.entity.User;
import com.wms.global.exception.exception.InputWarehouseDetailException;
import com.wms.global.exception.exception.InputWarehouseException;
import com.wms.global.exception.exception.PurchaseSheetException;
import com.wms.global.exception.responseCode.InputWarehouseDetailExceptionResponseCode;
import com.wms.global.exception.responseCode.InputWarehouseExceptionResponseCode;
import com.wms.global.exception.responseCode.PurchaseSheetExceptionResponseCode;
import com.wms.global.util.service.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InputWarehouseServiceImpl implements InputWarehouseService {

    private final InputWarehouseRepository inputWarehouseRepository;
    private final PurchaseSheetRepository
            purchaseSheetRepository;
    private final InputWarehouseDetailService inputWarehouseDetailService;
    private final UserFindService userFindService;
    private final InputWarehouseDetailRepository inputWarehouseDetailRepository;
    private final InventoryService inventoryService;
    private final PurchaseDetailService purchaseDetailService;
    private final PurchaseSheetService purchaseSheetService;

    @Override
    @Transactional
    public void createInputWarehouse(InputWarehouseRequestDTO inputWarehouseRequestDTO) {
        // 현재 사용자 찾기
        User user = userFindService.getCurrentUser();

        // 발주서 찾기
        PurchaseSheet purchaseSheet = purchaseSheetRepository.findById(inputWarehouseRequestDTO.getPurchaseSheetId())
                .orElseThrow(() -> new PurchaseSheetException(PurchaseSheetExceptionResponseCode.PURCHASE_SHEET_NOT_FOUND, "발주서를 찾을 수 없습니다."));

        // 입고서 생성
        InputWarehouse inputWarehouse = inputWarehouseRequestDTO.toEntity(user, purchaseSheet);
        inputWarehouseRepository.save(inputWarehouse);

        // 입고 상세 생성
        inputWarehouseDetailService.createInputWarehouseDetails(inputWarehouse, inputWarehouseRequestDTO.getInputWarehouseDetails());
    }

    @Override
    @Transactional(readOnly = true)
    public List<InputWarehouseResponseDTO> getInputWarehouseList() {
        return inputWarehouseRepository.findAllInputWarehousesWithDetails().stream()
                .map(InputWarehouseResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void completeInputWarehouse(List<Long> inputWarehouseDetailIds) {
        // 1. 해당 입고 상세 정보 조회
        List<InputWarehouseDetail> inputWarehouseDetails = inputWarehouseDetailRepository.findAllById(inputWarehouseDetailIds);

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
