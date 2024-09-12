package com.wms.domain.purchaseSheet.service;

import com.wms.domain.purchaseSheet.dto.request.PurchaseSheetRequestDTO;
import com.wms.domain.purchaseSheet.dto.response.PurchaseSheetResponseDTO;

import java.util.List;

public interface PurchaseSheetService {
    void createPurchaseSheet(PurchaseSheetRequestDTO purchaseSheetRequestDTO);

    List<PurchaseSheetResponseDTO> getAllPurchaseSheets();

}
