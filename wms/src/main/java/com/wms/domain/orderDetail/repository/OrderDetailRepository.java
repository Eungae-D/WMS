package com.wms.domain.orderDetail.repository;

import com.wms.domain.orderDetail.entity.OrderDetail;
import com.wms.domain.orderSheet.entity.OrderSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {

    @Query("SELECT od FROM OrderDetail od " +
            "JOIN FETCH od.item i " +
            "JOIN FETCH i.client c " +
            "LEFT JOIN FETCH i.inventory inv " +
            "JOIN FETCH od.orderSheet os " +
            "WHERE os.id = :orderSheetId")
    List<OrderDetail> findOrderDetailsByOrderSheetId(@Param("orderSheetId") Long orderSheetId);

}
