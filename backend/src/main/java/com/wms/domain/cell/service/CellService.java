package com.wms.domain.cell.service;

import com.wms.domain.cell.dto.request.CellRequestDTO;
import com.wms.domain.cell.dto.response.CellResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface CellService {
    // 셀 등록
    void registerCell(@Valid CellRequestDTO cellRequestDTO);

    // 셀 삭제
    void deleteCell(Long cellId);

    // 셀 목록 가져오기
    List<CellResponseDTO> getAllCells();

    // 셀 목록 가져오기(셀 코드)
    List<CellResponseDTO> getCellsByCode(String code);

    // 셀 목록 가져오기(셀명)
    List<CellResponseDTO> getCellsByName(String name);
}
