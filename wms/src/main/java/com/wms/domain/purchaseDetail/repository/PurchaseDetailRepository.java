package com.wms.domain.purchaseDetail.repository;

import com.wms.domain.purchaseDetail.entity.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Long> {

    @Query("SELECT pd FROM PurchaseDetail pd " +
            "JOIN FETCH pd.item i " +
            "JOIN FETCH pd.warehouse w " +
            "JOIN FETCH pd.purchaseSheet ps " +
            "JOIN FETCH ps.client c " +
            "JOIN FETCH ps.user u " +
            "JOIN FETCH u.department d " +
            "WHERE ps.id = :purchaseSheetId")
    List<PurchaseDetail> findPurchaseDetailsByPurchaseSheetId(@Param("purchaseSheetId") Long purchaseSheetId);
}
