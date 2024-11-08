package com.wms.domain.rack.service;

import com.wms.domain.rack.dto.request.RackRequestDTO;
import com.wms.domain.rack.dto.response.RackResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface RackService {
    // 랙 등록
    void registerRack(@Valid RackRequestDTO rackRequestDTO);

    // 랙 삭제
    void deleteRack(Long rackId);

    // 랙 목록 가져오기
    List<RackResponseDTO> getAllRacks();

    // 랙 목록 가져오기(랙 코드)
    List<RackResponseDTO> getRacksByCode(String code);

    // 랙 목록 가져오기(랙명)
    List<RackResponseDTO> getRacksByName(String name);
}
