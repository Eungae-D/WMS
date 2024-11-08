package com.wms.domain.orderDetail.controller;

import com.wms.domain.orderDetail.dto.response.OrderDetailResponseDTO;
import com.wms.domain.orderDetail.dto.response.OrderSheetDetailsResponseDTO;
import com.wms.domain.orderDetail.service.OrderDetailService;
import com.wms.global.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orderDetail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    // 수주서 상세 조회
    @GetMapping("/{orderSheetId}")
    public ResponseEntity<ApiResponse<?>> getOrderSheetDetails(@PathVariable Long orderSheetId) {
        OrderSheetDetailsResponseDTO orderSheetDetails = orderDetailService.getOrderSheetDetails(orderSheetId);
        return ResponseEntity.ok(ApiResponse.createSuccess(orderSheetDetails, "수주서 상세 조회 성공"));
    }
}
