package com.wms.domain.department.service;

import com.wms.domain.department.dto.request.DepartmentRequestDTO;
import jakarta.validation.Valid;

public interface DepartmentService {
    void registerDepartment(@Valid DepartmentRequestDTO departmentRequestDTO);
}
