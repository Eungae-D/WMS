package com.wms.domain.position.service.impl;

import com.wms.domain.position.dto.request.PositionRequestDTO;
import com.wms.domain.position.dto.response.PositionResponseDTO;
import com.wms.global.exception.exception.PositionException;
import com.wms.global.exception.responseCode.PositionExceptionResponseCode;
import com.wms.domain.position.entity.Position;
import com.wms.domain.position.repository.PositionRepository;
import com.wms.domain.position.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    // 직급 삭제
    @Override
    @Transactional
    public void deletePosition(Long positionId) {
        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new PositionException(PositionExceptionResponseCode.POSITION_NOT_FOUND, "직급을 찾을 수 없습니다."));

        // 직급 삭제
        positionRepository.delete(position);
    }

    // 직급 목록 가져오기
    @Override
    @Transactional(readOnly = true)
    public List<PositionResponseDTO> getAllPositions() {
        List<Position> positions = positionRepository.findAll();

        if (positions.isEmpty()) {
            throw new PositionException(PositionExceptionResponseCode.POSITION_NOT_FOUND, "직급을 찾을 수 없습니다.");
        }

        return positions.stream()
                .map(PositionResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 직급 목록 가져오기(직급 코드)
    @Override
    @Transactional(readOnly = true)
    public List<PositionResponseDTO> getPositionByCode(String positionCode) {
        List<Position> positions = positionRepository.findByPositionCode(positionCode);

        if (positions.isEmpty()) {
            throw new PositionException(PositionExceptionResponseCode.POSITION_NOT_FOUND, "직급을 찾을 수 없습니다.");
        }

        return positions.stream()
                .map(PositionResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 직급 목록 가져오기(직급 명)
    @Override
    @Transactional(readOnly = true)
    public List<PositionResponseDTO> getPositionByName(String positionName) {
        List<Position> positions = positionRepository.findByPositionName(positionName);

        if (positions.isEmpty()) {
            throw new PositionException(PositionExceptionResponseCode.POSITION_NOT_FOUND, "직급을 찾을 수 없습니다.");
        }

        return positions.stream()
                .map(PositionResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }


}