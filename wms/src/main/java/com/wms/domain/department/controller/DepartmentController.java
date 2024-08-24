package com.wms.domain.department.controller;

import com.wms.domain.department.dto.request.DepartmentRequestDTO;
import com.wms.domain.department.dto.response.DepartmentResponseDTO;
import com.wms.domain.department.service.DepartmentService;
import com.wms.global.util.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 부서 삭제
    @DeleteMapping("/delete/{departmentId}")
    public ResponseEntity<ApiResponse<?>> deleteDepartment(@PathVariable Long departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccessNoContent("부서삭제 성공."));
    }
    // 부서 목록 가져오기
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> getAllDepartments() {
        List<DepartmentResponseDTO> departmentList = departmentService.getAllDepartments();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(departmentList, "부서 목록 가져오기 성공."));
    }

    // 부서 목록 가져오기(부서 코드)
    @GetMapping("/code/{departmentCode}")
    public ResponseEntity<ApiResponse<?>> getDepartmentByCode(@PathVariable String departmentCode){
        List<DepartmentResponseDTO> departmentList = departmentService.getDepartmentByCode(departmentCode);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(departmentList, "부서 목록 가져오기 성공.(부서코드)"));
    }

    // 부서 목록 가져오기(부서명)
    @GetMapping("/name/{departmentName}")
    public ResponseEntity<ApiResponse<?>> getDepartmentByName(@PathVariable String departmentName) {
        List<DepartmentResponseDTO> departmentList = departmentService.getDepartmentByName(departmentName);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(departmentList, "부서 목록 가져오기 성공.(부서명)"));
    }



}
