package com.wms.domain.inputWarehouse.repository;

import com.wms.domain.inputWarehouse.entity.InputWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputWarehouseRepository extends JpaRepository<InputWarehouse, Long> {
}
