package com.wms.domain.department.service;

import com.wms.domain.department.dto.request.DepartmentRequestDTO;
import com.wms.domain.department.dto.response.DepartmentResponseDTO;
import com.wms.domain.department.entity.Department;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentService {
    // 부서 등록
    void registerDepartment(@Valid DepartmentRequestDTO departmentRequestDTO);

    // 부서 삭제
    void deleteDepartment(Long departmentId);

    // 부서 목록 가져오기
    List<DepartmentResponseDTO> getAllDepartments();

    // 부서 목록 가져오기(부서 코드)
    List<DepartmentResponseDTO> getDepartmentByCode(String departmentCode);

    // 부서 목록 가져오기(부서명)
    List<DepartmentResponseDTO> getDepartmentByName(String departmentName);
}
