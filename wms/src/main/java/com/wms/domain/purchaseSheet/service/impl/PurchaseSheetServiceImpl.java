package com.wms.domain.purchaseSheet.service.impl;

import com.wms.domain.client.entity.Client;
import com.wms.domain.client.repository.ClientRepository;
import com.wms.domain.item.entity.Item;
import com.wms.domain.item.repository.ItemRepository;
import com.wms.domain.orderSheet.entity.OrderSheet;
import com.wms.domain.orderSheet.repository.OrderSheetRepository;
import com.wms.domain.purchaseDetail.dto.request.PurchaseDetailRequestDTO;
import com.wms.domain.purchaseDetail.entity.PurchaseDetail;
import com.wms.domain.purchaseDetail.repository.PurchaseDetailRepository;
import com.wms.domain.purchaseDetail.service.PurchaseDetailService;
import com.wms.domain.purchaseSheet.dto.request.PurchaseSheetRequestDTO;
import com.wms.domain.purchaseSheet.dto.response.PurchaseSheetResponseDTO;
import com.wms.domain.purchaseSheet.entity.PurchaseSheet;
import com.wms.domain.purchaseSheet.repository.PurchaseSheetRepository;
import com.wms.domain.purchaseSheet.service.PurchaseSheetService;
import com.wms.domain.user.entity.User;
import com.wms.domain.warehouse.entity.Warehouse;
import com.wms.domain.warehouse.repository.WarehouseRepository;
import com.wms.global.exception.exception.*;
import com.wms.global.exception.responseCode.*;
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
public class PurchaseSheetServiceImpl implements PurchaseSheetService {
    private final PurchaseSheetRepository purchaseSheetRepository;
    private final OrderSheetRepository orderSheetRepository;
    private final UserFindService userFindService;
    private final ClientRepository clientRepository;
    private final PurchaseDetailService purchaseDetailService;
    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;

    @Override
    @Transactional
    public void createPurchaseSheet(PurchaseSheetRequestDTO purchaseSheetRequestDTO) {
        // 현재 사용자 찾기
        User user = userFindService.getCurrentUser();

        // 수주서 찾기
        OrderSheet orderSheet = orderSheetRepository.findById(purchaseSheetRequestDTO.getOrderSheetId())
                .orElseThrow(() -> new OrderSheetException(OrderSheetExceptionResponseCode.ORDER_SHEET_EMPTY, "수주서를 찾을 수 없습니다."));

        // 거래처 찾기
        Client client = clientRepository.findById(purchaseSheetRequestDTO.getClientId())
                .orElseThrow(() -> new ClientException(ClientExceptionResponseCode.CLIENT_NOT_FOUND, "거래처를 찾을 수 없습니다."));

        // 발주서 생성
        PurchaseSheet purchaseSheet = purchaseSheetRequestDTO.toEntity(orderSheet, user, client);
        purchaseSheetRepository.save(purchaseSheet);

        purchaseDetailService.createPurchaseDetails(purchaseSheet, purchaseSheetRequestDTO.getPurchaseDetails());
    }

    // 발주서 목록 가져오기
    @Override
    @Transactional(readOnly = true)
    public List<PurchaseSheetResponseDTO> getAllPurchaseSheets() {
        List<PurchaseSheet> purchaseSheets = purchaseSheetRepository.findAllPurchaseSheetsWithDetails();

        if (purchaseSheets.isEmpty()) {
            throw new PurchaseSheetException(PurchaseSheetExceptionResponseCode.PURCHASE_SHEET_NOT_FOUND, "발주서를 찾을 수 없습니다.");
        }

        return purchaseSheets.stream()
                .map(PurchaseSheetResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
