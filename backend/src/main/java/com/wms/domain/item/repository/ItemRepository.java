package com.wms.domain.item.repository;

import com.wms.domain.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    // 품목 코드로 검색
    @Query("SELECT i FROM Item i WHERE i.code LIKE %:code%")
    List<Item> findByCode(@Param("code") String code);

    // 품목명으로 검색
    @Query("SELECT i FROM Item i WHERE i.name LIKE %:name%")
    List<Item> findByNameContaining(@Param("name") String name);

    // 거래처명으로 검색
    @Query("SELECT i FROM Item i WHERE i.client.name LIKE %:clientName%")
    List<Item> findByClientNameContaining(@Param("clientName") String clientName);
}
