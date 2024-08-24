package com.wms.domain.client.repository;

import com.wms.domain.client.entity.Category;
import com.wms.domain.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // 거래처 코드 중복 확인
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Client c WHERE c.code = :code")
    boolean existsByCode(@Param("code") String code);

    // 카테고리별로 거래처 목록 조회
    @Query("SELECT c FROM Client c WHERE c.category = :category")
    List<Client> findAllByCategory(@Param("category") Category category);

    // 이름으로 거래처 목록 조회
    @Query("SELECT c FROM Client c WHERE c.name LIKE %:name%")
    List<Client> findByName(@Param("name") String name);

}
