package com.wms.domain.department.service;

import com.wms.domain.department.dto.request.DepartmentRequestDTO;
import jakarta.validation.Valid;

public interface DepartmentService {
    //부서 등록
    void registerDepartment(@Valid DepartmentRequestDTO departmentRequestDTO);

    //부서 삭제
    void deleteDepartment(Long departmentId);

//    DepartmentResponseDTO getDepartmentByCode(String departmentCode);
//    DepartmentResponseDTO getDepartmentByName(String departmentName);
}
