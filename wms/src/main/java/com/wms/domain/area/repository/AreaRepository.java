package com.wms.domain.area.repository;

import com.wms.domain.area.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository  extends JpaRepository<Area, Long> {

    // 구역 코드 중복 확인
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Area a WHERE a.code = :code")
    boolean existsByCode(@Param("code") String code);

    // 구역 이름 중복 확인
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Area a WHERE a.name = :name")
    boolean existsByName(@Param("name") String name);

    // 구역 코드로 구역 검색
    @Query("SELECT a FROM Area a WHERE a.code LIKE %:code%")
    List<Area> findAreasByCode(@Param("code") String code);

    // 구역명으로 구역 검색
    @Query("SELECT a FROM Area a WHERE a.name LIKE %:name%")
    List<Area> findAreasByName(@Param("name") String name);
}
