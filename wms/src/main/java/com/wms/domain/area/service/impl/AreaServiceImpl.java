package com.wms.domain.area.service.impl;

import com.wms.domain.area.dto.request.AreaRequestDTO;
import com.wms.domain.area.dto.response.AreaResponseDTO;
import com.wms.domain.area.entity.Area;
import com.wms.domain.area.repository.AreaRepository;
import com.wms.domain.area.service.AreaService;
import com.wms.domain.warehouse.entity.Warehouse;
import com.wms.domain.warehouse.repository.WarehouseRepository;
import com.wms.global.exception.exception.AreaException;
import com.wms.global.exception.exception.WarehouseException;
import com.wms.global.exception.responseCode.AreaExceptionResponseCode;
import com.wms.global.exception.responseCode.WarehouseExceptionResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {
    private final AreaRepository areaRepository;
    private final WarehouseRepository warehouseRepository;

    // 구역 등록
    @Override
    @Transactional
    public void registerArea(AreaRequestDTO areaRequestDTO) {
        if (areaRepository.existsByCode(areaRequestDTO.getCode())) {
            throw new AreaException(AreaExceptionResponseCode.AREA_CODE_DUPLICATE, "이미 존재하는 구역 코드입니다.");
        }

        if (areaRepository.existsByName(areaRequestDTO.getName())) {
            throw new AreaException(AreaExceptionResponseCode.AREA_NAME_DUPLICATE, "이미 존재하는 구역 이름입니다.");
        }

        Warehouse warehouse = warehouseRepository.findById(areaRequestDTO.getWarehouseId())
                .orElseThrow(() -> new WarehouseException(WarehouseExceptionResponseCode.WAREHOUSE_NOT_FOUND, "해당 창고를 찾을 수 없습니다."));

        Area area = areaRequestDTO.toEntity(warehouse);
        areaRepository.save(area);
    }

    // 구역 삭제
    @Override
    @Transactional
    public void deleteArea(Long areaId) {
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> new AreaException(AreaExceptionResponseCode.AREA_NOT_FOUND, "구역을 찾을 수 없습니다."));
        areaRepository.delete(area);
    }

    // 구역 목록 가져오기
    @Override
    @Transactional(readOnly = true)
    public List<AreaResponseDTO> getAllAreas() {
        List<Area> areas = areaRepository.findAll();
        return areas.stream()
                .map(AreaResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 구역 목록 가져오기(구역 코드)
    @Override
    @Transactional(readOnly = true)
    public List<AreaResponseDTO> getAreasByCode(String code) {
        List<Area> areas = areaRepository.findAreasByCode(code);

        if (areas.isEmpty()) {
            throw new AreaException(AreaExceptionResponseCode.AREA_NOT_FOUND, "구역을 찾을 수 없습니다.");
        }

        return areas.stream()
                .map(AreaResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 구역 목록 가져오기(구역명)
    @Override
    @Transactional(readOnly = true)
    public List<AreaResponseDTO> getAreasByName(String name) {
        List<Area> areas = areaRepository.findAreasByName(name);

        if (areas.isEmpty()) {
            throw new AreaException(AreaExceptionResponseCode.AREA_NOT_FOUND, "구역을 찾을 수 없습니다.");
        }

        return areas.stream()
                .map(AreaResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
