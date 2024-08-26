package com.wms.domain.cell.repository;

import com.wms.domain.cell.entity.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CellRepository extends JpaRepository<Cell, Long> {

    // 셀 코드 중복 확인
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cell c WHERE c.code = :code")
    boolean existsByCode(@Param("code") String code);

    // 셀 이름 중복 확인
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cell c WHERE c.name = :name")
    boolean existsByName(@Param("name") String name);

    // 셀 코드로 셀 검색
    @Query("SELECT c FROM Cell c WHERE c.code LIKE %:code%")
    List<Cell> findCellsByCode(@Param("code") String code);

    // 셀명으로 셀 검색
    @Query("SELECT c FROM Cell c WHERE c.name LIKE %:name%")
    List<Cell> findCellsByName(@Param("name") String name);
}
