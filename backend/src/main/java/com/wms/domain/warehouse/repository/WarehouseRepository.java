package com.wms.domain.warehouse.repository;

import com.wms.domain.warehouse.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    // 창고 코드 중복 확인
    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END FROM Warehouse w WHERE w.code = :code")
    boolean existsByCode(@Param("code") String code);

    // 창고 이름 중복 확인
    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END FROM Warehouse w WHERE w.name = :name")
    boolean existsByName(@Param("name") String name);

    // 창고 코드로 창고 검색
    @Query("SELECT w FROM Warehouse w WHERE w.code LIKE %:code%")
    List<Warehouse> findWarehousesByCode(@Param("code") String code);

    // 창고명으로 창고 검색
    @Query("SELECT w FROM Warehouse w WHERE w.name LIKE %:name%")
    List<Warehouse> findWarehousesByName(@Param("name") String name);
}
