package com.wms.domain.position.repository;

import com.wms.domain.position.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    //직급 코드 중복 확인

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Position p WHERE p.position_code = :positionCode")
    boolean existsByPositionCode(@Param("positionCode") String positionCode);
}
