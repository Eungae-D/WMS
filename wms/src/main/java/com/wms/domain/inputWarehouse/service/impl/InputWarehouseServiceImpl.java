package com.wms.domain.inputWarehouse.service.impl;

import com.wms.domain.inputWarehouse.dto.request.InputWarehouseRequestDTO;
import com.wms.domain.inputWarehouse.entity.InputWarehouse;
import com.wms.domain.inputWarehouse.repository.InputWarehouseRepository;
import com.wms.domain.inputWarehouse.service.InputWarehouseService;
import com.wms.domain.inputWarehouseDetail.service.InputWarehouseDetailService;
import com.wms.domain.purchaseSheet.entity.PurchaseSheet;
import com.wms.domain.purchaseSheet.repository.PurchaseSheetRepository;
import com.wms.domain.user.entity.User;
import com.wms.global.exception.exception.PurchaseSheetException;
import com.wms.global.exception.responseCode.PurchaseSheetExceptionResponseCode;
import com.wms.global.util.service.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InputWarehouseServiceImpl implements InputWarehouseService {

    private final InputWarehouseRepository inputWarehouseRepository;
    private final PurchaseSheetRepository purchaseSheetRepository;
    private final InputWarehouseDetailService inputWarehouseDetailService;
    private final UserFindService userFindService;

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
}
