package com.wms.domain.department.repository;

import com.wms.domain.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    //부서 코드 중복 확인
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Department d WHERE d.department_code = :departmentCode")
    boolean existsByDepartmentCode(@Param("departmentCode") String departmentCode);

    //부서 코드로 부서 검색
    @Query("SELECT d FROM Department d WHERE d.department_code LIKE %:departmentCode%")
    List<Department> findDepartmentsByCode(@Param("departmentCode") String departmentCode);

    // 부서명으로 부서 검색
    @Query("SELECT d FROM Department d WHERE d.department_name LIKE %:departmentName%")
    List<Department> findByDepartmentName(@Param("departmentName") String departmentName);
}
