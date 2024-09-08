package com.wms.domain.inputWarehouseDetail.repository;

import com.wms.domain.inputWarehouseDetail.entity.InputWarehouseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputWarehouseDetailRepository extends JpaRepository<InputWarehouseDetail, Long> {
}
