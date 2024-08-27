package com.wms.domain.area.service;

import com.wms.domain.area.dto.request.AreaRequestDTO;
import com.wms.domain.area.dto.response.AreaResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface AreaService {
    // 구역 등록
    void registerArea(@Valid AreaRequestDTO areaRequestDTO);

    // 구역 삭제
    void deleteArea(Long areaId);

    // 구역 목록 가져오기
    List<AreaResponseDTO> getAllAreas();

    // 구역 목록 가져오기(구역 코드)
    List<AreaResponseDTO> getAreasByCode(String code);

    // 구역 목록 가져오기(구역명)
    List<AreaResponseDTO> getAreasByName(String name);
}
