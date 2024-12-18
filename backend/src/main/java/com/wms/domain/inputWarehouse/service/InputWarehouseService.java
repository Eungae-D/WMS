package com.wms.domain.inputWarehouse.service;

import com.wms.domain.inputWarehouse.dto.request.InputWarehouseRequestDTO;
import com.wms.domain.inputWarehouse.dto.response.InputWarehouseResponseDTO;

import java.util.List;

public interface InputWarehouseService {
    // 입고 등록
    void createInputWarehouse(InputWarehouseRequestDTO inputWarehouseRequestDTO);

    // 입고 목록 조회
    List<InputWarehouseResponseDTO> getInputWarehouseList();

    // 입고 완료
    void completeInputWarehouse(List<Long> inputWarehouseDetailIds);
}
