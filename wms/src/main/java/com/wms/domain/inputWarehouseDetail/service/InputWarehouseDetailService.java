package com.wms.domain.inputWarehouseDetail.service;

import com.wms.domain.inputWarehouse.entity.InputWarehouse;
import com.wms.domain.inputWarehouseDetail.dto.request.InputWarehouseDetailRequestDTO;

import java.util.List;

public interface InputWarehouseDetailService {
    void createInputWarehouseDetails(InputWarehouse inputWarehouse, List<InputWarehouseDetailRequestDTO> inputWarehouseDetailRequestDTOs);
}
