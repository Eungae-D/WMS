package com.wms.domain.position.service.impl;

import com.wms.domain.department.dto.request.PositionRequestDTO;
import com.wms.global.exception.exception.PositionException;
import com.wms.global.exception.responseCode.PositionExceptionResponseCode;
import com.wms.domain.position.entity.Position;
import com.wms.domain.position.repository.PositionRepository;
import com.wms.domain.position.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;


    // 직급 등록
    @Override
    @Transactional
    public void registerPosition(PositionRequestDTO positionRequestDTO){

        // 직급코드 중복 확인
        if(positionRepository.existsByPositionCode(positionRequestDTO.getPosition_code())){
            throw new PositionException(PositionExceptionResponseCode.POSITION_CODE_DUPLICATE,"이미 존재하는 직급코드입니다.");
        }

        //직급 저장 로직
        Position position = positionRequestDTO.toEntity();
        positionRepository.save(position);
    }

}
