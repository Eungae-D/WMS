package com.wms.domain.orderSheet.repository;

import com.wms.domain.orderSheet.entity.OrderSheet;
import com.wms.domain.orderSheet.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderSheetRepository extends JpaRepository<OrderSheet, Long> {

    @Query("SELECT os FROM OrderSheet os " +
            "JOIN FETCH os.user u " +
            "JOIN FETCH os.client c " +
            "JOIN FETCH os.orderDetails od " +
            "JOIN FETCH od.item i")
    List<OrderSheet> findAllOrderSheetsWithDetails();
}
