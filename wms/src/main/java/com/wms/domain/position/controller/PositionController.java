package com.wms.domain.position.controller;

import com.wms.domain.department.dto.request.PositionRequestDTO;
import com.wms.domain.position.service.PositionService;
import com.wms.global.util.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/rank")
public class PositionController {
    private final PositionService positionService;


    // 직급 등록
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> positionRegister(@Valid @RequestBody PositionRequestDTO positionRequestDTO){
        positionService.registerPosition(positionRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("직급등록 성공."));
    }

}
