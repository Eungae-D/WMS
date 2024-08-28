package com.wms.domain.orderSheet.service;

import com.wms.domain.orderSheet.dto.request.OrderSheetRequestDTO;

public interface OrderSheetService {

    // 수주서 등록
    void createOrderSheet(OrderSheetRequestDTO orderSheetRequestDTO);
}
