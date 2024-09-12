package com.wms.domain.orderSheet.service.impl;

import com.wms.domain.client.entity.Client;
import com.wms.domain.client.repository.ClientRepository;
import com.wms.domain.orderDetail.service.OrderDetailService;
import com.wms.domain.orderSheet.dto.request.OrderSheetRequestDTO;
import com.wms.domain.orderSheet.dto.response.OrderSheetResponseDTO;
import com.wms.domain.orderSheet.entity.OrderSheet;
import com.wms.domain.orderSheet.repository.OrderSheetRepository;
import com.wms.domain.orderSheet.service.OrderSheetService;
import com.wms.domain.user.entity.User;
import com.wms.global.exception.exception.ClientException;
import com.wms.global.exception.exception.OrderSheetException;
import com.wms.global.exception.responseCode.ClientExceptionResponseCode;
import com.wms.global.exception.responseCode.OrderSheetExceptionResponseCode;
import com.wms.global.util.service.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderSheetServiceImpl implements OrderSheetService {

    private final OrderSheetRepository orderSheetRepository;
    private final ClientRepository clientRepository;
    private final OrderDetailService orderDetailService;
    private final UserFindService userFindService;

    // 수주서 등록
    @Override
    @Transactional
    public void createOrderSheet(OrderSheetRequestDTO orderSheetRequestDTO) {
        User user = userFindService.getCurrentUser();
        Client client = clientRepository.findById(orderSheetRequestDTO.getClientId())
                .orElseThrow(() -> new ClientException(ClientExceptionResponseCode.CLIENT_NOT_FOUND,"거래처를 찾을 수 없습니다."));

        // 품목 리스트가 존재하는지 검사
        if (orderSheetRequestDTO.getOrderDetails() == null || orderSheetRequestDTO.getOrderDetails().isEmpty()) {
            throw new OrderSheetException(OrderSheetExceptionResponseCode.ORDER_DETAILS_EMPTY, "수주서에 품목이 존재하지 않습니다.");
        }

        OrderSheet orderSheet = orderSheetRequestDTO.toEntity(user, client);

        orderSheetRepository.save(orderSheet);

        orderDetailService.createOrderDetails(orderSheet, orderSheetRequestDTO.getOrderDetails());
    }

    // 수주서 목록 가져오기
    @Override
    @Transactional(readOnly = true)
    public List<OrderSheetResponseDTO> getAllOrderSheets() {
        List<OrderSheet> orderSheets = orderSheetRepository.findAllOrderSheetsWithDetails();

        if (orderSheets.isEmpty()) {
            throw new OrderSheetException(OrderSheetExceptionResponseCode.ORDER_SHEETS_EMPTY, "수주서 목록이 비어 있습니다.");
        }

        return orderSheets.stream()
                .map(OrderSheetResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 수주서 삭제
    @Override
    @Transactional
    public void deleteOrderSheet(Long orderSheetId) {
        OrderSheet orderSheet = orderSheetRepository.findById(orderSheetId)
                .orElseThrow(() -> new OrderSheetException(OrderSheetExceptionResponseCode.ORDER_SHEET_EMPTY, "수주서를 찾을 수 없습니다."));

        orderSheetRepository.delete(orderSheet);
    }
}
