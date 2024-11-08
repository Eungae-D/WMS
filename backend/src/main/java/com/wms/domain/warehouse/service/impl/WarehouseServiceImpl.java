package com.wms.domain.warehouse.service.impl;

import com.wms.domain.warehouse.dto.request.WarehouseRequestDTO;
import com.wms.domain.warehouse.dto.response.WarehouseResponseDTO;
import com.wms.domain.warehouse.entity.Warehouse;
import com.wms.domain.warehouse.repository.WarehouseRepository;
import com.wms.domain.warehouse.service.WarehouseService;
import com.wms.global.exception.exception.WarehouseException;
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
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;

    // 창고 등록
    @Override
    @Transactional
    public void registerWarehouse(WarehouseRequestDTO warehouseRequestDTO){
        // 창고 코드 중복 확인
        if (warehouseRepository.existsByCode(warehouseRequestDTO.getCode())) {
            throw new WarehouseException(WarehouseExceptionResponseCode.WAREHOUSE_CODE_DUPLICATE, "이미 존재하는 창고 코드입니다.");
        }

        // 창고 이름 중복 확인
        if (warehouseRepository.existsByName(warehouseRequestDTO.getName())) {
            throw new WarehouseException(WarehouseExceptionResponseCode.WAREHOUSE_NAME_DUPLICATE, "이미 존재하는 창고 이름입니다.");
        }

        Warehouse warehouse = warehouseRequestDTO.toEntity();
        warehouseRepository.save(warehouse);
    }

    // 창고 삭제
    @Override
    @Transactional
    public void deleteWarehouse(Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new WarehouseException(WarehouseExceptionResponseCode.WAREHOUSE_NOT_FOUND, "창고를 찾을 수 없습니다."));
        warehouseRepository.delete(warehouse);
    }

    // 창고 목록 가져오기
    @Override
    @Transactional(readOnly = true)
    public List<WarehouseResponseDTO> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return warehouses.stream()
                .map(WarehouseResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 창고 목록 가져오기(창고 코드)
    @Override
    @Transactional(readOnly = true)
    public List<WarehouseResponseDTO> getWarehousesByCode(String code) {
        List<Warehouse> warehouses = warehouseRepository.findWarehousesByCode(code);

        if (warehouses.isEmpty()) {
            throw new WarehouseException(WarehouseExceptionResponseCode.WAREHOUSE_NOT_FOUND, "창고를 찾을 수 없습니다.");
        }

        return warehouses.stream()
                .map(WarehouseResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 창고 목록 가져오기(창고명)
    @Override
    @Transactional(readOnly = true)
    public List<WarehouseResponseDTO> getWarehousesByName(String name) {
        List<Warehouse> warehouses = warehouseRepository.findWarehousesByName(name);

        if (warehouses.isEmpty()) {
            throw new WarehouseException(WarehouseExceptionResponseCode.WAREHOUSE_NOT_FOUND, "창고를 찾을 수 없습니다.");
        }

        return warehouses.stream()
                .map(WarehouseResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
