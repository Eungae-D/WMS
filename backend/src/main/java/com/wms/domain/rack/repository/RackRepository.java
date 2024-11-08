package com.wms.domain.rack.repository;

import com.wms.domain.rack.entity.Rack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RackRepository extends JpaRepository<Rack, Long> {

    // 랙 코드 중복 확인
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Rack r WHERE r.code = :code")
    boolean existsByCode(@Param("code") String code);

    // 랙 이름 중복 확인
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Rack r WHERE r.name = :name")
    boolean existsByName(@Param("name") String name);

    // 랙 코드로 랙 검색
    @Query("SELECT r FROM Rack r WHERE r.code LIKE %:code%")
    List<Rack> findRacksByCode(@Param("code") String code);

    // 랙명으로 랙 검색
    @Query("SELECT r FROM Rack r WHERE r.name LIKE %:name%")
    List<Rack> findRacksByName(@Param("name") String name);
}
