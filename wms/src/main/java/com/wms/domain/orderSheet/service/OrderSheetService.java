package com.wms.domain.orderSheet.service;

import com.wms.domain.orderSheet.dto.request.OrderSheetRequestDTO;
import com.wms.domain.orderSheet.dto.response.OrderSheetResponseDTO;

import java.util.List;

public interface OrderSheetService {

    // 수주서 등록
    void createOrderSheet(OrderSheetRequestDTO orderSheetRequestDTO);

    // 수주서 목록 가져오기
    List<OrderSheetResponseDTO> getAllOrderSheets();

    // 수주서 삭제
    void deleteOrderSheet(Long orderSheetId);
}
