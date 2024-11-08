package com.wms.domain.user.repository;

import com.wms.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //이메일 중복
    boolean existsByEmail(String email);

    //이메일로 유저 찾기
    Optional<User> findByEmail(String email);

    //이름 부서 직책이 같은 사람 조회
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM User u " +
            "WHERE u.name = :userName AND u.department.id = :deptId AND u.position.id = :posId")
    boolean existsByNameAndDepartmentIdAndPositionId(@Param("userName") String userName, @Param("deptId") Long deptId, @Param("posId") Long posId);

    // 유저 부서 직급 패치 조인
    @Query("SELECT u FROM User u JOIN FETCH u.department JOIN FETCH u.position ORDER BY u.id ASC")
    List<User> findAllWithDepartmentAndPosition();
}
