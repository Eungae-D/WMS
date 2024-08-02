package com.wms.domain.department.controller;

import com.wms.domain.department.dto.request.DepartmentRequestDTO;
import com.wms.domain.department.service.DepartmentService;
import com.wms.global.util.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    // 부서 등록
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> departmentRegister(@Valid @RequestBody DepartmentRequestDTO departmentRequestDTO){
        departmentService.registerDepartment(departmentRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("부서등록 성공."));
    }

}
