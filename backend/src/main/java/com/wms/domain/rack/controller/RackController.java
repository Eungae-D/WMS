package com.wms.domain.rack.controller;

import com.wms.domain.rack.dto.request.RackRequestDTO;
import com.wms.domain.rack.dto.response.RackResponseDTO;
import com.wms.domain.rack.service.RackService;
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
@RequestMapping("/api/v1/rack")
public class RackController {

    private final RackService rackService;

    // 랙 등록
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerRack(@Valid @RequestBody RackRequestDTO rackRequestDTO) {
        rackService.registerRack(rackRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("랙 등록 성공."));
    }

    // 랙 삭제
    @DeleteMapping("/delete/{rackId}")
    public ResponseEntity<ApiResponse<?>> deleteRack(@PathVariable Long rackId) {
        rackService.deleteRack(rackId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccessNoContent("랙 삭제 성공."));
    }

    // 랙 목록 가져오기
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> getAllRacks() {
        List<RackResponseDTO> rackList = rackService.getAllRacks();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(rackList, "랙 목록 가져오기 성공."));
    }

    // 랙 목록 가져오기(랙 코드로 검색)
    @GetMapping("/code/{code}")
    public ResponseEntity<ApiResponse<?>> getRacksByCode(@PathVariable String code) {
        List<RackResponseDTO> rackList = rackService.getRacksByCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(rackList, "랙 목록 가져오기 성공.(코드 검색)"));
    }

    // 랙 목록 가져오기(랙명으로 검색)
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<?>> getRacksByName(@PathVariable String name) {
        List<RackResponseDTO> rackList = rackService.getRacksByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(rackList, "랙 목록 가져오기 성공.(이름 검색)"));
    }
}
