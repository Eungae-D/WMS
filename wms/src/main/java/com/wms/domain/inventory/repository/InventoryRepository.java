package com.wms.domain.inventory.repository;

import com.wms.domain.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {


    @Query("SELECT inv FROM Inventory inv " +
            "JOIN FETCH inv.item i " +
            "JOIN FETCH inv.warehouse w " +
            "JOIN FETCH inv.area a " +
            "JOIN FETCH inv.rack r " +
            "JOIN FETCH inv.cell c " +
            "JOIN FETCH inv.lot l")
    List<Inventory> findAllWithDetails();
}
