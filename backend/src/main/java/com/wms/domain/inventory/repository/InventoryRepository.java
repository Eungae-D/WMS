package com.wms.domain.inventory.repository;

import com.wms.domain.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    // 전체 재고 목록 조회
    @Query("SELECT inv FROM Inventory inv " +
            "JOIN FETCH inv.item i " +
            "JOIN FETCH inv.warehouse w " +
            "JOIN FETCH inv.area a " +
            "JOIN FETCH inv.rack r " +
            "JOIN FETCH inv.cell c " +
            "JOIN FETCH inv.lot l")
    List<Inventory> findAllWithDetails();

    // 창고에 속한 재고 목록 조회
    @Query("SELECT inv FROM Inventory inv " +
            "JOIN FETCH inv.item i " +
            "JOIN FETCH inv.warehouse w " +
            "JOIN FETCH inv.area a " +
            "JOIN FETCH inv.rack r " +
            "JOIN FETCH inv.cell c " +
            "JOIN FETCH inv.lot l " +
            "WHERE w.id = :warehouseId")
    List<Inventory> findAllByWarehouseId(Long warehouseId);

    // 창고, 구역에 속한 재고 목록 조회
    @Query("SELECT inv FROM Inventory inv " +
            "JOIN FETCH inv.item i " +
            "JOIN FETCH inv.warehouse w " +
            "JOIN FETCH inv.area a " +
            "JOIN FETCH inv.rack r " +
            "JOIN FETCH inv.cell c " +
            "JOIN FETCH inv.lot l " +
            "WHERE w.id = :warehouseId AND a.id = :areaId")
    List<Inventory> findAllByWarehouseIdAndAreaId(Long warehouseId, Long areaId);

    // 창고, 구역, 랙에 속한 재고 목록 조회
    @Query("SELECT inv FROM Inventory inv " +
            "JOIN FETCH inv.item i " +
            "JOIN FETCH inv.warehouse w " +
            "JOIN FETCH inv.area a " +
            "JOIN FETCH inv.rack r " +
            "JOIN FETCH inv.cell c " +
            "JOIN FETCH inv.lot l " +
            "WHERE w.id = :warehouseId AND a.id = :areaId AND r.id = :rackId")
    List<Inventory> findAllByWarehouseIdAndAreaIdAndRackId(Long warehouseId, Long areaId, Long rackId);

    // 창고, 구역, 랙, 셀에 속한 재고 목록 조회
    @Query("SELECT inv FROM Inventory inv " +
            "JOIN FETCH inv.item i " +
            "JOIN FETCH inv.warehouse w " +
            "JOIN FETCH inv.area a " +
            "JOIN FETCH inv.rack r " +
            "JOIN FETCH inv.cell c " +
            "JOIN FETCH inv.lot l " +
            "WHERE w.id = :warehouseId AND a.id = :areaId AND r.id = :rackId AND c.id = :cellId")
    List<Inventory> findAllByWarehouseIdAndAreaIdAndRackIdAndCellId(Long warehouseId, Long areaId, Long rackId, Long cellId);

    @Query("SELECT inv FROM Inventory inv " +
            "JOIN FETCH inv.item i " +
            "JOIN FETCH inv.warehouse w " +
            "JOIN FETCH inv.area a " +
            "JOIN FETCH inv.rack r " +
            "JOIN FETCH inv.cell c " +
            "JOIN FETCH inv.lot l " +
            "WHERE i.id = :itemId " +
            "AND w.id = :warehouseId " +
            "AND a.id = :areaId " +
            "AND r.id = :rackId " +
            "AND c.id = :cellId " +
            "AND l.id = :lotId")
    Optional<Inventory> findByItemAndWarehouseAndAreaAndRackAndCellAndLot(Long itemId, Long warehouseId, Long areaId, Long rackId, Long cellId, Long lotId);

}
