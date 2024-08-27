package com.wms.domain.lot.controller;

import com.wms.domain.lot.dto.response.LotResponseDTO;
import com.wms.domain.lot.service.LotService;
import com.wms.global.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/lot")
public class LotController {

    private final LotService lotService;

    // 로트 정보 목록 가져오기
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> getAllLots() {
        List<LotResponseDTO> lotList = lotService.getAllLots();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(lotList, "로트 목록 가져오기 성공."));
    }
}
