package com.wms.domain.lot.repository;

import com.wms.domain.lot.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {

    // 오늘 날짜의 로트 개수를 가져오는 메서드
    @Query("SELECT COUNT(l) FROM Lot l WHERE l.lotNumber LIKE :datePrefix%")
    int countByLotNumberPrefix(@Param("datePrefix") String datePrefix);

    // 로트 번호 중복 확인
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Lot l WHERE l.lotNumber =:lotNumber")
    boolean existsByLotNumber(@Param("lotNumber")String lotNumber);
}
