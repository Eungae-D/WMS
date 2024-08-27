package com.wms.domain.item.controller;

import com.wms.domain.item.dto.request.ItemRequestDTO;
import com.wms.domain.item.dto.response.ItemResponseDTO;
import com.wms.domain.item.service.ItemService;
import com.wms.global.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/item")
public class ItemController {
    private final ItemService itemService;

    // 상품 등록
    @PostMapping(value = "/register", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<?>> registerItem(@Validated @RequestPart("itemRequest") ItemRequestDTO itemRequestDTO, @RequestPart(value = "itemImage", required = false) MultipartFile itemImage) {

        itemRequestDTO = itemRequestDTO.withItemImage(itemImage);  // 이미지를 DTO에 설정
        itemService.registerItem(itemRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("상품이 성공적으로 등록되었습니다."));
    }

    // 상품 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<?>> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccessNoContent("상품이 성공적으로 삭제되었습니다."));
    }

    // 전체 상품 목록 가져오기
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> getAllItems() {
        List<ItemResponseDTO> items = itemService.getAllItems();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(items, "전체 상품 목록 조회 성공"));
    }

    // 품목 코드로 상품 목록 가져오기
    @GetMapping("/code/{code}")
    public ResponseEntity<ApiResponse<?>> getItemsByCode(@PathVariable String code) {
        List<ItemResponseDTO> items = itemService.getItemsByCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(items, "품목 코드로 상품 목록 조회 성공"));
    }

    // 품목명으로 상품 목록 가져오기
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<?>> getItemsByName(@PathVariable String name) {
        List<ItemResponseDTO> items = itemService.getItemsByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(items, "품목명으로 상품 목록 조회 성공"));
    }

    // 거래처명으로 상품 목록 가져오기
    @GetMapping("/client/{clientName}")
    public ResponseEntity<ApiResponse<?>> getItemsByClientName(@PathVariable String clientName) {
        List<ItemResponseDTO> items = itemService.getItemsByClientName(clientName);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(items, "거래처명으로 상품 목록 조회 성공"));
    }

}
