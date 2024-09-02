package com.wms.domain.orderDetail.service;

import com.wms.domain.orderDetail.dto.request.OrderDetailRequestDTO;
import com.wms.domain.orderDetail.dto.response.OrderDetailResponseDTO;
import com.wms.domain.orderSheet.entity.OrderSheet;

import java.util.List;

public interface OrderDetailService {

    // 수주서 품목 생성
    void createOrderDetails(OrderSheet orderSheet, List<OrderDetailRequestDTO> orderDetailRequestDTOs);

    // 수주서 상세 정보 조회
    List<OrderDetailResponseDTO> getOrderSheetDetails(Long orderSheetId);
}
