package com.wms.domain.orderSheet.repository;

import com.wms.domain.orderSheet.entity.OrderSheet;
import com.wms.domain.orderSheet.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderSheetRepository extends JpaRepository<OrderSheet, Long> {

    List<OrderSheet> findByStatus(Status status);
}
