package com.wms.domain.cell.controller;

import com.wms.domain.cell.dto.request.CellRequestDTO;
import com.wms.domain.cell.dto.response.CellResponseDTO;
import com.wms.domain.cell.service.CellService;
import com.wms.global.util.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cell")
public class CellController {

    private final CellService cellService;

    // 셀 등록
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerCell(@Valid @RequestBody CellRequestDTO cellRequestDTO) {
        cellService.registerCell(cellRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("셀 등록 성공."));
    }

    // 셀 삭제
    @DeleteMapping("/delete/{cellId}")
    public ResponseEntity<ApiResponse<?>> deleteCell(@PathVariable Long cellId) {
        cellService.deleteCell(cellId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccessNoContent("셀 삭제 성공."));
    }

    // 셀 목록 가져오기
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> getAllCells() {
        List<CellResponseDTO> cellList = cellService.getAllCells();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(cellList, "셀 목록 가져오기 성공."));
    }

    // 셀 목록 가져오기(셀 코드로 검색)
    @GetMapping("/code/{code}")
    public ResponseEntity<ApiResponse<?>> getCellsByCode(@PathVariable String code) {
        List<CellResponseDTO> cellList = cellService.getCellsByCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(cellList, "셀 목록 가져오기 성공.(코드 검색)"));
    }

    // 셀 목록 가져오기(셀명으로 검색)
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<?>> getCellsByName(@PathVariable String name) {
        List<CellResponseDTO> cellList = cellService.getCellsByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(cellList, "셀 목록 가져오기 성공.(이름 검색)"));
    }
}
