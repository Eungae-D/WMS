package com.wms.domain.department.service.impl;

import com.wms.domain.department.dto.request.DepartmentRequestDTO;
import com.wms.domain.department.entity.Department;
import com.wms.domain.department.repository.DepartmentRepository;
import com.wms.domain.department.service.DepartmentService;
import com.wms.domain.exception.exception.DepartmentException;
import com.wms.domain.exception.responseCode.DepartmentExceptionResponseCode;
import com.wms.domain.user.entity.User;
import com.wms.global.util.service.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        //부서 코드 중복 확인
        if(departmentRepository.existsByDepartmentCode(departmentRequestDTO.getDepartment_code())){
            throw new DepartmentException(DepartmentExceptionResponseCode.DEPARTMENT_CODE_DUPLICATE, "이미 존재하는 부서코드입니다.");
        }

        //부서 저장 로직
        Department department = departmentRequestDTO.toEntity();
        departmentRepository.save(department);
    }

}
