package com.wms.domain.client.service;

import com.wms.domain.client.dto.request.ClientRequestDTO;
import com.wms.domain.client.dto.response.ClientResponseDTO;
import com.wms.domain.client.entity.Category;
import jakarta.validation.Valid;

import java.util.List;

public interface ClientService {

    // 거래처 등록
    void registerClient(@Valid ClientRequestDTO clientRequestDTO);

    // 거래처 삭제
    void deleteClient(Long clientId);

    // 거래처 목록 가져오기
    List<ClientResponseDTO> getAllClients();

    // 카테고리별로 거래처 목록 조회
    List<ClientResponseDTO> getClientsByCategory(Category category);

    // 이름으로 거래처 목록 조회
    List<ClientResponseDTO> getClientsByName(String name);
}
