package com.wms.domain.item.service;

import com.wms.domain.item.dto.request.ItemRequestDTO;
import com.wms.domain.item.dto.response.ItemResponseDTO;

import java.util.List;

public interface ItemService {
    // 상품 등록
    void registerItem(ItemRequestDTO itemRequestDTO);

    // 상품 삭제
    void deleteItem(Long id);

    // 상품 목록 가져오기
    List<ItemResponseDTO> getAllItems();

    // 상품 코드로 검색
    List<ItemResponseDTO> getItemsByCode(String code);

    // 상품명으로 검색
    List<ItemResponseDTO> getItemsByName(String name);

    // 거래처명으로 검색
    List<ItemResponseDTO> getItemsByClientName(String clientName);
}
