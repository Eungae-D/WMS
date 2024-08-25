package com.wms.domain.area.controller;

import com.wms.domain.area.dto.request.AreaRequestDTO;
import com.wms.domain.area.dto.response.AreaResponseDTO;
import com.wms.domain.area.service.AreaService;
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
@RequestMapping("/api/v1/area")
public class AreaController {
    private final AreaService areaService;

    // 구역 등록
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerArea(@Valid @RequestBody AreaRequestDTO areaRequestDTO) {
        areaService.registerArea(areaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("구역 등록 성공."));
    }

    // 구역 삭제
    @DeleteMapping("/delete/{areaId}")
    public ResponseEntity<ApiResponse<?>> deleteArea(@PathVariable Long areaId) {
        areaService.deleteArea(areaId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccessNoContent("구역 삭제 성공."));
    }

    // 구역 목록 가져오기
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> getAllAreas() {
        List<AreaResponseDTO> areaList = areaService.getAllAreas();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(areaList, "구역 목록 가져오기 성공."));
    }

    // 구역 목록 가져오기(구역 코드로 검색)
    @GetMapping("/code/{code}")
    public ResponseEntity<ApiResponse<?>> getAreasByCode(@PathVariable String code) {
        List<AreaResponseDTO> areaList = areaService.getAreasByCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(areaList, "구역 목록 가져오기 성공.(코드 검색)"));
    }

    // 구역 목록 가져오기(구역명으로 검색)
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<?>> getAreasByName(@PathVariable String name) {
        List<AreaResponseDTO> areaList = areaService.getAreasByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(areaList, "구역 목록 가져오기 성공.(이름 검색)"));
    }
}
