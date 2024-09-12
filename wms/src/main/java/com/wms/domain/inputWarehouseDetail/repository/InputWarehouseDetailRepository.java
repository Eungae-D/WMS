package com.wms.domain.inputWarehouseDetail.repository;

import com.wms.domain.inputWarehouseDetail.entity.InputWarehouseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputWarehouseDetailRepository extends JpaRepository<InputWarehouseDetail, Long> {

    @Query("SELECT iwd FROM InputWarehouseDetail iwd " +
            "JOIN FETCH iwd.inputWarehouse iw " +
            "JOIN FETCH iwd.item i " +
            "JOIN FETCH iwd.warehouse w " +
            "JOIN FETCH iwd.area a " +
            "JOIN FETCH iwd.rack r " +
            "JOIN FETCH iwd.cell c " +
            "JOIN FETCH iwd.lot l " +
            "WHERE iw.id = :inputWarehouseId")
    List<InputWarehouseDetail> findAllByInputWarehouseId(@Param("inputWarehouseId") Long inputWarehouseId);

    @Query("SELECT iwd FROM InputWarehouseDetail iwd " +
            "JOIN FETCH iwd.inputWarehouse iw " +
            "JOIN FETCH iwd.purchaseDetail pd " +
            "JOIN FETCH pd.purchaseSheet ps " +
            "JOIN FETCH iwd.item i " +
            "JOIN FETCH iwd.warehouse w " +
            "JOIN FETCH iwd.area a " +
            "JOIN FETCH iwd.rack r " +
            "JOIN FETCH iwd.cell c " +
            "JOIN FETCH iwd.lot l " +
            "WHERE ps.id = :purchaseSheetId")
    List<InputWarehouseDetail> findAllByPurchaseSheetId(@Param("purchaseSheetId") Long purchaseSheetId);
}
