package com.wms.domain.purchaseDetail.service;

import com.wms.domain.purchaseDetail.dto.request.PurchaseDetailRequestDTO;
import com.wms.domain.purchaseDetail.dto.response.PurchaseSheetDetailsResponseDTO;
import com.wms.domain.purchaseSheet.entity.PurchaseSheet;

import java.util.List;

public interface PurchaseDetailService {

    // 발주서 등록
    void createPurchaseDetails(PurchaseSheet purchaseSheet, List<PurchaseDetailRequestDTO> purchaseDetailRequestDTOs);

    // 발주서 상세 정보 조회
    PurchaseSheetDetailsResponseDTO getPurchaseSheetDetails(Long purchaseSheetId);
}
