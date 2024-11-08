package com.wms.domain.inputWarehouse.repository;

import com.wms.domain.inputWarehouse.entity.InputWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputWarehouseRepository extends JpaRepository<InputWarehouse, Long> {

    @Query("SELECT iw FROM InputWarehouse iw " +
            "JOIN FETCH iw.user u " +
            "JOIN FETCH iw.purchaseSheet ps " +
            "JOIN FETCH ps.client c " +
            "JOIN FETCH iw.inputWarehouseDetails iwd " +
            "JOIN FETCH iwd.item i")
    List<InputWarehouse> findAllInputWarehousesWithDetails();
}
