package com.wms.domain.department.repository;

import com.wms.domain.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    //부서 코드 중복 확인
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Department d WHERE d.department_code = :departmentCode")
    boolean existsByDepartmentCode(@Param("departmentCode") String departmentCode);
}
