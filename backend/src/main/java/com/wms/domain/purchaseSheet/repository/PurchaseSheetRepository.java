package com.wms.domain.purchaseSheet.repository;

import com.wms.domain.purchaseSheet.entity.PurchaseSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseSheetRepository extends JpaRepository<PurchaseSheet, Long> {

    @Query("SELECT ps FROM PurchaseSheet ps " +
            "JOIN FETCH ps.user u " +
            "JOIN FETCH ps.client c " +
            "JOIN FETCH ps.purchaseDetails pd " +
            "JOIN FETCH pd.item i")
    List<PurchaseSheet> findAllPurchaseSheetsWithDetails();
    //
}
