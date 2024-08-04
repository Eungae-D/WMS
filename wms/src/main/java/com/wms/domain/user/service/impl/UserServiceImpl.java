package com.wms.domain.user.service.impl;

import com.wms.domain.department.entity.Department;
import com.wms.domain.department.repository.DepartmentRepository;
import com.wms.domain.exception.exception.DepartmentException;
import com.wms.domain.exception.exception.PositionException;
import com.wms.domain.exception.exception.UserException;
import com.wms.domain.exception.responseCode.DepartmentExceptionResponseCode;
import com.wms.domain.exception.responseCode.PositionExceptionResponseCode;
import com.wms.domain.exception.responseCode.UserExceptionResponseCode;
import com.wms.domain.position.entity.Position;
import com.wms.domain.position.repository.PositionRepository;
import com.wms.domain.user.dto.request.EmailRequestDTO;
import com.wms.domain.user.dto.request.SignUpRequestDTO;
import com.wms.domain.user.entity.User;
import com.wms.domain.user.repository.UserRepository;
import com.wms.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final PasswordEncoder encoder;

    // 이메일 중복확인
    @Override
    @Transactional(readOnly = true)
    public boolean emailCheck(EmailRequestDTO emailRequestDTO){

        return userRepository.existsByEmail(emailRequestDTO.getEmail());
    }

    // 회원가입
    @Override
    @Transactional
    public void register(SignUpRequestDTO signUpRequestDTO){

        //이메일 중복 확인
        if(userRepository.existsByEmail(signUpRequestDTO.getEmail())){
            throw new UserException(UserExceptionResponseCode.EXISTS_USER, "이미 존재하는 유저입니다.");
        }

        //부서
        Department department = departmentRepository.findById(signUpRequestDTO.getDepartmentId())
                .orElseThrow(()-> new DepartmentException(DepartmentExceptionResponseCode.DEPARTMENT_NOT_FOUND, "부서를 찾을 수 없습니다."));

        //직책
        Position position = positionRepository.findById(signUpRequestDTO.getPositionId())
                .orElseThrow(() -> new PositionException(PositionExceptionResponseCode.POSITION_NOT_FOUND, "직급을 찾을 수 없습니다."));

        // 같은 이름, 부서, 직급을 가진 사용자가 있는지 확인
        if (userRepository.existsByNameAndDepartmentIdAndPositionId(signUpRequestDTO.getName(), signUpRequestDTO.getDepartmentId(), signUpRequestDTO.getPositionId())) {
            throw new UserException(UserExceptionResponseCode.USER_EXISTS_IN_DEPARTMENT_AND_POSITION, "이름, 부서, 직급이 같은 사용자가 이미 존재합니다.");
        }

        User user = signUpRequestDTO.toEntity(encoder.encode(signUpRequestDTO.getPassword()),department,position);
        userRepository.save(user);
    }
}
