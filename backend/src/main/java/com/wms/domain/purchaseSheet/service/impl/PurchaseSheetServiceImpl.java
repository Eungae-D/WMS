package com.wms.domain.purchaseSheet.service.impl;

import com.wms.domain.client.entity.Client;
import com.wms.domain.client.repository.ClientRepository;
import com.wms.domain.item.entity.Item;
import com.wms.domain.item.repository.ItemRepository;
import com.wms.domain.orderSheet.entity.OrderSheet;
import com.wms.domain.orderSheet.repository.OrderSheetRepository;
import com.wms.domain.purchaseDetail.dto.request.PurchaseDetailRequestDTO;
import com.wms.domain.purchaseDetail.entity.PurchaseDetail;
import com.wms.domain.purchaseDetail.entity.Status;
import com.wms.domain.purchaseDetail.service.PurchaseDetailService;
import com.wms.domain.purchaseSheet.dto.request.PurchaseSheetRequestDTO;
import com.wms.domain.purchaseSheet.dto.response.PurchaseSheetResponseDTO;
import com.wms.domain.purchaseSheet.entity.PurchaseSheet;
import com.wms.domain.purchaseSheet.entity.PurchaseType;
import com.wms.domain.purchaseSheet.repository.PurchaseSheetRepository;
import com.wms.domain.purchaseSheet.service.PurchaseSheetService;
import com.wms.domain.user.entity.User;
import com.wms.domain.warehouse.repository.WarehouseRepository;
import com.wms.global.exception.exception.*;
import com.wms.global.exception.responseCode.*;
import com.wms.global.util.service.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

        // 발주 상태 결정 변수
        PurchaseType purchaseType;

        // 수주서 찾기 (수주서 ID가 있을 경우)
        OrderSheet orderSheet = null;
        if (purchaseSheetRequestDTO.getOrderSheetId() != null) {
            orderSheet = orderSheetRepository.findById(purchaseSheetRequestDTO.getOrderSheetId())
                    .orElseThrow(() -> new OrderSheetException(OrderSheetExceptionResponseCode.ORDER_SHEET_EMPTY, "수주서를 찾을 수 없습니다."));
            purchaseType = PurchaseType.ORDER_SHEET_BASED; // 수주서로 인한 입고
        } else {
            purchaseType = PurchaseType.OTHER_BASED; // 기타 입고
        }

        // 발주 상세 품목의 발주처 검증
        validateSameClient(purchaseSheetRequestDTO.getPurchaseDetails());

        // 거래처 찾기
        Client client = clientRepository.findById(purchaseSheetRequestDTO.getClientId())
                .orElseThrow(() -> new ClientException(ClientExceptionResponseCode.CLIENT_NOT_FOUND, "거래처를 찾을 수 없습니다."));

        // 발주서 생성
        PurchaseSheet purchaseSheet = purchaseSheetRequestDTO.toEntity(orderSheet, user, client,purchaseType);
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

    // 발주서 품목 취급처가 같으면 여러개 발주 등록 가능
    private void validateSameClient(List<PurchaseDetailRequestDTO> purchaseDetails) {
        Optional<Long> firstClientId = Optional.empty();

        for (PurchaseDetailRequestDTO detailRequest : purchaseDetails) {
            Long itemId = detailRequest.getItemId();
            Item item = itemRepository.findById(itemId)
                    .orElseThrow(() -> new ItemException(ItemExceptionResponseCode.ITEM_NOT_FOUND, "품목을 찾을 수 없습니다."));

            Long clientId = item.getClient().getId();

            if (firstClientId.isEmpty()) {
                firstClientId = Optional.of(clientId);  // 첫 번째 품목의 거래처 저장
            } else if (!firstClientId.get().equals(clientId)) {
                // 거래처가 다르면 예외 발생
                throw new ClientException(ClientExceptionResponseCode.CLIENT_DIFFERENT, "다른 거래처의 품목은 한 번에 등록할 수 없습니다.");
            }
        }
    }

    @Override
    @Transactional
    public void updatePurchaseSheetStatus(PurchaseSheet purchaseSheet) {
        boolean allCompleted = true; // 모든 발주 상세가 COMPLETED인지 확인

        for (PurchaseDetail detail : purchaseSheet.getPurchaseDetails()) {
            if (detail.getStatus() != Status.COMPLETED) {
                allCompleted = false;
                break;
            }
        }

        if (allCompleted) {
            purchaseSheet.completed();
        } else {
            purchaseSheet.ordering();
        }

        // 상태 변경 후 저장
        purchaseSheetRepository.save(purchaseSheet);
    }

}
