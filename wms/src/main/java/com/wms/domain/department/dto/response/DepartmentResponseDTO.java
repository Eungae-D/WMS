package com.wms.domain.department.dto.response;

import com.wms.domain.department.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DepartmentResponseDTO {
    private Long id;
    private String department_code;
    private String department_name;

    public static DepartmentResponseDTO fromEntity(Department department) {
        return DepartmentResponseDTO.builder()
                .id(department.getId())
                .department_code(department.getDepartment_code())
                .department_name(department.getDepartment_name())
                .build();
    }
}
