package com.wms.domain.purchaseSheet.service;

import com.wms.domain.purchaseSheet.dto.request.PurchaseSheetRequestDTO;
import com.wms.domain.purchaseSheet.dto.response.PurchaseSheetResponseDTO;
import com.wms.domain.purchaseSheet.entity.PurchaseSheet;

import java.util.List;

public interface PurchaseSheetService {
    void createPurchaseSheet(PurchaseSheetRequestDTO purchaseSheetRequestDTO);

    List<PurchaseSheetResponseDTO> getAllPurchaseSheets();

    void updatePurchaseSheetStatus(PurchaseSheet purchaseSheet);

}
