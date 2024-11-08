package com.wms.domain.department.dto.request;

import com.wms.domain.department.entity.Department;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DepartmentRequestDTO {

    @NotBlank(message = "부서코드가 존재하지 않습니다.")
    private String department_code;

    @NotBlank(message = "부서명이 존재하지 않습니다.")
    private String department_name;

    public Department toEntity(){
        return Department.builder()
                .department_code(this.department_code)
                .department_name(this.department_name)
                .build();
    }
}
