package com.wms.domain.purchaseDetail.service.Impl;

import com.wms.domain.item.entity.Item;
import com.wms.domain.item.repository.ItemRepository;
import com.wms.domain.purchaseDetail.dto.request.PurchaseDetailRequestDTO;
import com.wms.domain.purchaseDetail.dto.response.PurchaseDetailResponseDTO;
import com.wms.domain.purchaseDetail.dto.response.PurchaseSheetDetailsResponseDTO;
import com.wms.domain.purchaseDetail.entity.PurchaseDetail;
import com.wms.domain.purchaseDetail.repository.PurchaseDetailRepository;
import com.wms.domain.purchaseDetail.service.PurchaseDetailService;
import com.wms.domain.purchaseSheet.entity.PurchaseSheet;
import com.wms.domain.purchaseSheet.repository.PurchaseSheetRepository;
import com.wms.domain.warehouse.entity.Warehouse;
import com.wms.domain.warehouse.repository.WarehouseRepository;
import com.wms.global.exception.exception.ItemException;
import com.wms.global.exception.exception.PurchaseSheetException;
import com.wms.global.exception.exception.WarehouseException;
import com.wms.global.exception.responseCode.ItemExceptionResponseCode;
import com.wms.global.exception.responseCode.PurchaseSheetExceptionResponseCode;
import com.wms.global.exception.responseCode.WarehouseExceptionResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseDetailServiceImpl implements PurchaseDetailService {

    private final PurchaseDetailRepository purchaseDetailRepository;
    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;
    private final PurchaseSheetRepository purchaseSheetRepository;

    @Override
    @Transactional
    public void createPurchaseDetails(PurchaseSheet purchaseSheet, List<PurchaseDetailRequestDTO> purchaseDetailRequestDTOs) {
        for (PurchaseDetailRequestDTO detailRequest : purchaseDetailRequestDTOs) {
            Item item = itemRepository.findById(detailRequest.getItemId())
                    .orElseThrow(() -> new ItemException(ItemExceptionResponseCode.ITEM_NOT_FOUND, "품목을 찾을 수 없습니다."));

            Warehouse warehouse = warehouseRepository.findById(detailRequest.getWarehouseId())
                    .orElseThrow(() -> new WarehouseException(WarehouseExceptionResponseCode.WAREHOUSE_NOT_FOUND, "창고를 찾을 수 없습니다."));

            // 발주 상세 생성
            PurchaseDetail purchaseDetail = detailRequest.toEntity(purchaseSheet, item, warehouse);
            purchaseDetailRepository.save(purchaseDetail);
        }
    }

    // 발주서 상세 조회
    @Override
    @Transactional(readOnly = true)
    public PurchaseSheetDetailsResponseDTO getPurchaseSheetDetails(Long purchaseSheetId) {
        // 발주서 정보 조회
        PurchaseSheet purchaseSheet = purchaseSheetRepository.findById(purchaseSheetId)
                .orElseThrow(() -> new PurchaseSheetException(PurchaseSheetExceptionResponseCode.PURCHASE_SHEET_NOT_FOUND, "발주서를 찾을 수 없습니다."));

        // 발주서에 해당하는 품목 리스트 조회
        List<PurchaseDetail> purchaseDetails = purchaseDetailRepository.findPurchaseDetailsByPurchaseSheetId(purchaseSheetId);

        if (purchaseDetails.isEmpty()) {
            throw new PurchaseSheetException(PurchaseSheetExceptionResponseCode.PURCHASE_SHEET_NOT_FOUND, "발주서에 품목이 존재하지 않습니다.");
        }

        // 품목 리스트를 DTO로 변환
        List<PurchaseDetailResponseDTO> purchaseDetailResponseDTOs = purchaseDetails.stream()
                .map(PurchaseDetailResponseDTO::fromEntity)
                .collect(Collectors.toList());

        // 발주서 정보와 품목 리스트를 합쳐서 DTO로 변환
        return PurchaseSheetDetailsResponseDTO.fromEntity(purchaseSheet, purchaseDetailResponseDTOs);
    }
}
