package com.wms.domain.position.controller;

import com.wms.domain.department.dto.request.PositionRequestDTO;
import com.wms.domain.position.dto.response.PositionResponseDTO;
import com.wms.domain.position.service.PositionService;
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
@RequestMapping("/api/v1/position")
public class PositionController {
    private final PositionService positionService;


    // 직급 등록
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> positionRegister(@Valid @RequestBody PositionRequestDTO positionRequestDTO){
        positionService.registerPosition(positionRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("직급등록 성공."));
    }

    // 직급 삭제
    @DeleteMapping("/delete/{positionId}")
    public ResponseEntity<ApiResponse<?>> deletePosition(@PathVariable Long positionId) {
        positionService.deletePosition(positionId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccessNoContent("직급삭제 성공."));
    }

    // 직급 목록 가져오기
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> getAllPositions() {
        List<PositionResponseDTO> positionList = positionService.getAllPositions();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(positionList, "직급 목록 가져오기 성공."));
    }

    // 직급 목록 가져오기(직급 코드)
    @GetMapping("/code/{positionCode}")
    public ResponseEntity<ApiResponse<?>> getPositionByCode(@PathVariable String positionCode) {
        List<PositionResponseDTO> positions = positionService.getPositionByCode(positionCode);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(positions, "직급 코드로 검색 성공."));
    }

    // 직급 목록 가져오기(직급명)
    @GetMapping("/name/{positionName}")
    public ResponseEntity<ApiResponse<?>> getPositionByName(@PathVariable String positionName) {
        List<PositionResponseDTO> positions = positionService.getPositionByName(positionName);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(positions, "직책 코드로 검색 성공."));
    }


}
