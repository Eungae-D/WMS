package com.wms.domain.client.controller;

import com.wms.domain.client.dto.request.ClientRequestDTO;
import com.wms.domain.client.dto.response.ClientResponseDTO;
import com.wms.domain.client.entity.Category;
import com.wms.domain.client.service.ClientService;
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
@RequestMapping("/api/v1/client")
public class ClientController {
    private final ClientService clientService;

    // 거래처 등록
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> clientRegister(@Valid @RequestBody ClientRequestDTO clientRequestDTO){
        clientService.registerClient(clientRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("거래처등록 성공."));
    }

    // 거래처 삭제
    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<ApiResponse<?>> deleteClient(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccessNoContent("거래처 삭제 성공."));
    }

    // 거래처 목록 조회(ALL)
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> getAllClients(){
        List<ClientResponseDTO> clientList = clientService.getAllClients();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(clientList,"거래처 목록 조회 성공."));
    }

    // 발주처 목록 조회 (ORDER)
    @GetMapping("/orders")
    public ResponseEntity<ApiResponse<?>> getClientsByOrderCategory() {
        List<ClientResponseDTO> clients = clientService.getClientsByCategory(Category.ORDER);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(clients, "발주처 목록 조회 성공."));
    }

    // 수주처 목록 조회 (PURCHASE)
    @GetMapping("/purchases")
    public ResponseEntity<ApiResponse<?>> getClientsByPurchaseCategory() {
        List<ClientResponseDTO> clients = clientService.getClientsByCategory(Category.PURCHASE);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(clients, "수주처 목록 조회 성공."));
    }

    // 거래처 이름으로 조회
    @GetMapping("/name/{clientName}")
    public ResponseEntity<ApiResponse<?>> getClientsByName(@PathVariable String clientName) {
        List<ClientResponseDTO> clients = clientService.getClientsByName(clientName);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(clients, "거래처 이름으로 검색 성공."));
    }
}
