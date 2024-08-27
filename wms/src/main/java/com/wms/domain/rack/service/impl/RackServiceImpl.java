package com.wms.domain.rack.service.impl;

import com.wms.domain.area.entity.Area;
import com.wms.domain.area.repository.AreaRepository;
import com.wms.domain.rack.dto.request.RackRequestDTO;
import com.wms.domain.rack.dto.response.RackResponseDTO;
import com.wms.domain.rack.entity.Rack;
import com.wms.domain.rack.repository.RackRepository;
import com.wms.domain.rack.service.RackService;
import com.wms.global.exception.exception.AreaException;
import com.wms.global.exception.exception.RackException;
import com.wms.global.exception.responseCode.AreaExceptionResponseCode;
import com.wms.global.exception.responseCode.RackExceptionResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RackServiceImpl implements RackService {

    private final RackRepository rackRepository;
    private final AreaRepository areaRepository;

    // 랙 등록
    @Override
    @Transactional
    public void registerRack(RackRequestDTO rackRequestDTO) {
        if (rackRepository.existsByCode(rackRequestDTO.getCode())) {
            throw new RackException(RackExceptionResponseCode.RACK_CODE_DUPLICATE, "이미 존재하는 랙 코드입니다.");
        }

        if (rackRepository.existsByName(rackRequestDTO.getName())) {
            throw new RackException(RackExceptionResponseCode.RACK_NAME_DUPLICATE, "이미 존재하는 랙 이름입니다.");
        }

        Area area = areaRepository.findById(rackRequestDTO.getAreaId())
                .orElseThrow(() -> new AreaException(AreaExceptionResponseCode.AREA_NOT_FOUND, "해당 구역을 찾을 수 없습니다."));

        Rack rack = rackRequestDTO.toEntity(area);
        rackRepository.save(rack);
    }

    // 랙 삭제
    @Override
    @Transactional
    public void deleteRack(Long rackId) {
        Rack rack = rackRepository.findById(rackId)
                .orElseThrow(() -> new RackException(RackExceptionResponseCode.RACK_NOT_FOUND, "랙을 찾을 수 없습니다."));
        rackRepository.delete(rack);
    }

    // 랙 목록 가져오기
    @Override
    @Transactional(readOnly = true)
    public List<RackResponseDTO> getAllRacks() {
        List<Rack> racks = rackRepository.findAll();
        return racks.stream()
                .map(RackResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 랙 목록 가져오기(랙 코드)
    @Override
    @Transactional(readOnly = true)
    public List<RackResponseDTO> getRacksByCode(String code) {
        List<Rack> racks = rackRepository.findRacksByCode(code);

        if (racks.isEmpty()) {
            throw new RackException(RackExceptionResponseCode.RACK_NOT_FOUND, "랙을 찾을 수 없습니다.");
        }

        return racks.stream()
                .map(RackResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 랙 목록 가져오기(랙명)
    @Override
    @Transactional(readOnly = true)
    public List<RackResponseDTO> getRacksByName(String name) {
        List<Rack> racks = rackRepository.findRacksByName(name);

        if (racks.isEmpty()) {
            throw new RackException(RackExceptionResponseCode.RACK_NOT_FOUND, "랙을 찾을 수 없습니다.");
        }

        return racks.stream()
                .map(RackResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
