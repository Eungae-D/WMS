package com.wms.domain.orderDetail.service.impl;

import com.wms.domain.item.entity.Item;
import com.wms.domain.item.repository.ItemRepository;
import com.wms.domain.orderDetail.dto.request.OrderDetailRequestDTO;
import com.wms.domain.orderDetail.dto.response.OrderDetailResponseDTO;
import com.wms.domain.orderDetail.entity.OrderDetail;
import com.wms.domain.orderDetail.repository.OrderDetailRepository;
import com.wms.domain.orderDetail.service.OrderDetailService;
import com.wms.domain.orderSheet.entity.OrderSheet;
import com.wms.global.exception.exception.ItemException;
import com.wms.global.exception.exception.OrderSheetException;
import com.wms.global.exception.responseCode.ItemExceptionResponseCode;
import com.wms.global.exception.responseCode.OrderSheetExceptionResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final ItemRepository itemRepository;

    // 수주서 등록(품목)
    @Override
    @Transactional
    public void createOrderDetails(OrderSheet orderSheet, List<OrderDetailRequestDTO> orderDetailRequestDTOs) {
        for (OrderDetailRequestDTO detailRequest : orderDetailRequestDTOs) {
            Item item = itemRepository.findById(detailRequest.getItemId())
                    .orElseThrow(()-> new ItemException(ItemExceptionResponseCode.ITEM_NOT_FOUND,"품목을 찾을 수 없습니다."));

            OrderDetail orderDetail = detailRequest.toEntity(orderSheet, item);

            orderDetailRepository.save(orderDetail);
        }
    }

    // 수주서 상세 조회
    @Override
    @Transactional(readOnly = true)
    public List<OrderDetailResponseDTO> getOrderSheetDetails(Long orderSheetId) {

        List<OrderDetail> orderDetails = orderDetailRepository.findOrderDetailsByOrderSheetId(orderSheetId);

        if (orderDetails.isEmpty()) {
            throw new OrderSheetException(OrderSheetExceptionResponseCode.ORDER_SHEET_EMPTY, "수주서를 찾을 수 없습니다.");
        }

        return orderDetails.stream()
                .map(OrderDetailResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
