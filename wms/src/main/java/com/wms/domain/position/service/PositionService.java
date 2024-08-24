package com.wms.domain.position.service;

import com.wms.domain.position.dto.request.PositionRequestDTO;
import com.wms.domain.position.dto.response.PositionResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface PositionService {

    // 직급 등록
    void registerPosition(@Valid PositionRequestDTO positionRequestDTO);

    // 직급 삭제
    void deletePosition(Long positionId);

    // 직급 목록 가져오기
    List<PositionResponseDTO> getAllPositions();

    // 직급 코드로 검색
    List<PositionResponseDTO> getPositionByCode(String positionCode);

    // 직책 코드로 검색
    List<PositionResponseDTO> getPositionByName(String titleCode);
}
