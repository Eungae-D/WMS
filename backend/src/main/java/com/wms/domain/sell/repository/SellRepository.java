package com.wms.domain.sell.repository;

import com.wms.domain.sell.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellRepository extends JpaRepository<Sell, Long> {

    @Query("SELECT s FROM Sell s " +
            "JOIN FETCH s.user u " +
            "JOIN FETCH s.orderSheet o " +
            "JOIN FETCH o.client c "+
            "JOIN FETCH s.sellDetails sd")
    List<Sell> findAllSellsWithDetails();
}
