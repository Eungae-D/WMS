package com.wms.domain.inputWarehouse.service;

import com.wms.domain.inputWarehouse.dto.request.InputWarehouseRequestDTO;

public interface InputWarehouseService {
    // 입고 등록
    void createInputWarehouse(InputWarehouseRequestDTO inputWarehouseRequestDTO);

}
