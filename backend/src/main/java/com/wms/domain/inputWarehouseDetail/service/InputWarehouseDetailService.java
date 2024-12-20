package com.wms.domain.inputWarehouseDetail.service;

import com.wms.domain.inputWarehouse.entity.InputWarehouse;
import com.wms.domain.inputWarehouseDetail.dto.request.InputWarehouseDetailCompleteDTO;
import com.wms.domain.inputWarehouseDetail.dto.request.InputWarehouseDetailRequestDTO;
import com.wms.domain.inputWarehouseDetail.dto.response.InputWarehouseDetailsResponseDTO;
import com.wms.domain.inputWarehouseDetail.dto.response.InputWarehouseDetailsResponseDTO2;

import java.util.List;

public interface InputWarehouseDetailService {

    // 입고서 등록
    void createInputWarehouseDetails(InputWarehouse inputWarehouse, List<InputWarehouseDetailRequestDTO> inputWarehouseDetailRequestDTOs);

    // 입고서 상세 정보 조회 (입고 번호)
    List<InputWarehouseDetailsResponseDTO> getInputWarehouseDetails(Long inputWarehouseId);

    // 입고서 상세 정보 조회 (발주서 번호)
    List<InputWarehouseDetailsResponseDTO2> getInputWarehouseDetailsByPurchaseSheetId(Long purchaseSheetId);

    // 입고 완료
    void completeInputWarehouse(List<InputWarehouseDetailCompleteDTO> inputWarehouseDetailIds);
}
