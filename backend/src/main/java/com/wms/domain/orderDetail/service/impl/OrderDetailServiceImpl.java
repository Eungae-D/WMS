package com.wms.domain.orderDetail.service.impl;

import com.wms.domain.item.entity.Item;
import com.wms.domain.item.repository.ItemRepository;
import com.wms.domain.orderDetail.dto.request.OrderDetailRequestDTO;
import com.wms.domain.orderDetail.dto.response.OrderDetailResponseDTO;
import com.wms.domain.orderDetail.dto.response.OrderSheetDetailsResponseDTO;
import com.wms.domain.orderDetail.dto.response.sell.OrderDetailSellDTO;
import com.wms.domain.orderDetail.dto.response.sell.OrderDetailSellResponseDTO;
import com.wms.domain.orderDetail.dto.response.sell.OrderSheetDetailsSellResponseDTO;
import com.wms.domain.orderDetail.entity.OrderDetail;
import com.wms.domain.orderDetail.repository.OrderDetailRepository;
import com.wms.domain.orderDetail.service.OrderDetailService;
import com.wms.domain.orderSheet.entity.OrderSheet;
import com.wms.domain.orderSheet.repository.OrderSheetRepository;
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
    private final OrderSheetRepository orderSheetRepository;
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
    public OrderSheetDetailsResponseDTO getOrderSheetDetails(Long orderSheetId) {
        // 수주서 정보 조회
        OrderSheet orderSheet = orderSheetRepository.findById(orderSheetId)
                .orElseThrow(() -> new OrderSheetException(OrderSheetExceptionResponseCode.ORDER_SHEET_EMPTY, "수주서를 찾을 수 없습니다."));

        // 수주서에 해당하는 품목 리스트 조회
        List<OrderDetail> orderDetails = orderDetailRepository.findOrderDetailsByOrderSheetId(orderSheetId);

        if (orderDetails.isEmpty()) {
            throw new OrderSheetException(OrderSheetExceptionResponseCode.ORDER_SHEET_EMPTY, "수주서에 품목이 존재하지 않습니다.");
        }

        // 품목 리스트를 DTO로 변환
        List<OrderDetailResponseDTO> orderDetailResponseDTOs = orderDetails.stream()
                .map(OrderDetailResponseDTO::fromEntity)
                .collect(Collectors.toList());

        // 수주서 정보와 품목 리스트를 합쳐서 DTO로 변환
        return OrderSheetDetailsResponseDTO.fromEntity(orderSheet, orderDetailResponseDTOs);
        }

    @Override
    @Transactional(readOnly = true)
    public OrderSheetDetailsSellResponseDTO getOrderSheetDetailsWithSell(Long orderSheetId) {
        // 수주서 정보 조회
        OrderSheet orderSheet = orderSheetRepository.findById(orderSheetId)
                .orElseThrow(() -> new OrderSheetException(OrderSheetExceptionResponseCode.ORDER_SHEET_EMPTY, "수주서를 찾을 수 없습니다."));

        // OrderDetail 조회 (Lot 포함)
        List<OrderDetail> orderDetails = orderDetailRepository.findOrderDetailsWithLotsByOrderSheetId(orderSheetId);

        if (orderDetails.isEmpty()) {
            throw new OrderSheetException(OrderSheetExceptionResponseCode.ORDER_SHEET_EMPTY, "수주서에 품목이 존재하지 않습니다.");
        }

        // OrderDetail -> DTO 변환
        List<OrderDetailSellResponseDTO> sellResponseDTOs = orderDetails.stream().map(orderDetail -> {
            // Item의 Inventory 기반으로 SellDetails 생성
            List<OrderDetailSellDTO> sellDetails = orderDetail.getItem().getInventory().stream()
                    .map(OrderDetailSellDTO::fromEntity) // fromEntity 메서드 사용
                    .collect(Collectors.toList());

            // OrderDetailSellResponseDTO 생성
            return OrderDetailSellResponseDTO.fromEntity(orderDetail, sellDetails);
        }).collect(Collectors.toList());


        // 수주서 정보와 품목 리스트를 합쳐서 DTO로 변환
        return OrderSheetDetailsSellResponseDTO.fromEntity(orderSheet, sellResponseDTOs);
    }


}
