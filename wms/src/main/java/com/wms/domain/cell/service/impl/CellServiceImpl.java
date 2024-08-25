package com.wms.domain.cell.service.impl;

import com.wms.domain.cell.dto.request.CellRequestDTO;
import com.wms.domain.cell.dto.response.CellResponseDTO;
import com.wms.domain.cell.entity.Cell;
import com.wms.domain.cell.repository.CellRepository;
import com.wms.domain.cell.service.CellService;
import com.wms.domain.rack.entity.Rack;
import com.wms.domain.rack.repository.RackRepository;
import com.wms.global.exception.exception.CellException;
import com.wms.global.exception.exception.RackException;
import com.wms.global.exception.responseCode.CellExceptionResponseCode;
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
public class CellServiceImpl implements CellService {

    private final CellRepository cellRepository;
    private final RackRepository rackRepository;

    // 셀 등록
    @Override
    @Transactional
    public void registerCell(CellRequestDTO cellRequestDTO) {
        if (cellRepository.existsByCode(cellRequestDTO.getCode())) {
            throw new CellException(CellExceptionResponseCode.CELL_CODE_DUPLICATE, "이미 존재하는 셀 코드입니다.");
        }

        if (cellRepository.existsByName(cellRequestDTO.getName())) {
            throw new CellException(CellExceptionResponseCode.CELL_NAME_DUPLICATE, "이미 존재하는 셀 이름입니다.");
        }

        Rack rack = rackRepository.findById(cellRequestDTO.getRackId())
                .orElseThrow(() -> new RackException(RackExceptionResponseCode.RACK_NOT_FOUND, "해당 랙을 찾을 수 없습니다."));

        Cell cell = cellRequestDTO.toEntity(rack);
        cellRepository.save(cell);
    }

    // 셀 삭제
    @Override
    @Transactional
    public void deleteCell(Long cellId) {
        Cell cell = cellRepository.findById(cellId)
                .orElseThrow(() -> new CellException(CellExceptionResponseCode.CELL_NOT_FOUND, "셀을 찾을 수 없습니다."));
        cellRepository.delete(cell);
    }

    // 셀 목록 가져오기
    @Override
    @Transactional(readOnly = true)
    public List<CellResponseDTO> getAllCells() {
        List<Cell> cells = cellRepository.findAll();
        if (cells.isEmpty()) {
            throw new CellException(CellExceptionResponseCode.CELL_NOT_FOUND, "셀을 찾을 수 없습니다.");
        }
        return cells.stream()
                .map(CellResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 셀 목록 가져오기(셀 코드)
    @Override
    @Transactional(readOnly = true)
    public List<CellResponseDTO> getCellsByCode(String code) {
        List<Cell> cells = cellRepository.findCellsByCode(code);

        if (cells.isEmpty()) {
            throw new CellException(CellExceptionResponseCode.CELL_NOT_FOUND, "셀을 찾을 수 없습니다.");
        }

        return cells.stream()
                .map(CellResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 셀 목록 가져오기(셀명)
    @Override
    @Transactional(readOnly = true)
    public List<CellResponseDTO> getCellsByName(String name) {
        List<Cell> cells = cellRepository.findCellsByName(name);

        if (cells.isEmpty()) {
            throw new CellException(CellExceptionResponseCode.CELL_NOT_FOUND, "셀을 찾을 수 없습니다.");
        }

        return cells.stream()
                .map(CellResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
