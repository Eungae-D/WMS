package com.wms.domain.department.service.impl;

import com.wms.domain.department.dto.request.DepartmentRequestDTO;
import com.wms.domain.department.dto.response.DepartmentResponseDTO;
import com.wms.domain.department.entity.Department;
import com.wms.domain.department.repository.DepartmentRepository;
import com.wms.domain.department.service.DepartmentService;
import com.wms.global.exception.exception.DepartmentException;
import com.wms.global.exception.responseCode.DepartmentExceptionResponseCode;
import com.wms.global.util.service.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final UserFindService userFindService;

    // 부서 등록
    @Override
    @Transactional
    public void registerDepartment(DepartmentRequestDTO departmentRequestDTO){
        if(departmentRepository.existsByDepartmentCode(departmentRequestDTO.getDepartment_code())){
            throw new DepartmentException(DepartmentExceptionResponseCode.DEPARTMENT_CODE_DUPLICATE, "이미 존재하는 부서코드입니다.");
        }

        Department department = departmentRequestDTO.toEntity();
        departmentRepository.save(department);
    }

    // 부서 삭제
    @Override
    @Transactional
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentException(DepartmentExceptionResponseCode.DEPARTMENT_NOT_FOUND, "부서를 찾을 수 없습니다."));
        departmentRepository.delete(department);
    }
    // 부서 목록 가져오기
    @Override
    @Transactional(readOnly = true)
    public List<DepartmentResponseDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(DepartmentResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    //부서 목록 가져오기(부서 코드)
    @Override
    @Transactional(readOnly = true)
    public List<DepartmentResponseDTO> getDepartmentByCode(String departmentCode) {
        List<Department> departments = departmentRepository.findDepartmentsByCode(departmentCode);

        if (departments.isEmpty()) {
            throw new DepartmentException(DepartmentExceptionResponseCode.DEPARTMENT_NOT_FOUND, "부서를 찾을 수 없습니다.");
        }

        return departments.stream()
                .map(DepartmentResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }


    //부서 목록 가져오기(부서명)
    @Override
    @Transactional(readOnly = true)
    public List<DepartmentResponseDTO> getDepartmentByName(String departmentName) {
        List<Department> departments = departmentRepository.findByDepartmentName(departmentName);

        if (departments.isEmpty()) {
            throw new DepartmentException(DepartmentExceptionResponseCode.DEPARTMENT_NOT_FOUND, "부서를 찾을 수 없습니다.");
        }

        return departments.stream()
                .map(DepartmentResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
