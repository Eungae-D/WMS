package com.wms.domain.item.service.impl;

import com.wms.domain.client.entity.Client;
import com.wms.domain.client.repository.ClientRepository;
import com.wms.domain.item.dto.request.ItemRequestDTO;
import com.wms.domain.item.dto.response.ItemResponseDTO;
import com.wms.domain.item.entity.Item;
import com.wms.domain.item.repository.ItemRepository;
import com.wms.domain.item.service.ItemService;
import com.wms.global.exception.exception.ClientException;
import com.wms.global.exception.exception.ItemException;
import com.wms.global.exception.responseCode.ClientExceptionResponseCode;
import com.wms.global.exception.responseCode.ItemExceptionResponseCode;
import com.wms.global.util.s3.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Value("${default.item.image.url}")
    private String defaultItemImageUrl;

    private final ItemRepository itemRepository;
    private final ClientRepository clientRepository;
    private final S3Service s3Service;

    // 상품 등록
    @Override
    @Transactional
    public void registerItem(ItemRequestDTO itemRequestDTO){

        // 거래처 확인
        Client client = clientRepository.findById(itemRequestDTO.getClientId())
                .orElseThrow(()-> new ClientException(ClientExceptionResponseCode.CLIENT_NOT_FOUND, "거래처를 찾을 수 없습니다."));

        // 이미지 업로드
        String itemImageUrl = null;
        if (itemRequestDTO.getItemImage() != null && !itemRequestDTO.getItemImage().isEmpty()) {
            itemImageUrl = s3Service.uploadFile(itemRequestDTO.getItemImage(), "items");
        }else{
            itemImageUrl = defaultItemImageUrl; // 기본 이미지 URL 설정
        }

        Item item = Item.builder()
                .code(itemRequestDTO.getCode())
                .name(itemRequestDTO.getName())
                .unitPrice(itemRequestDTO.getUnitPrice())
                .shippingPrice(itemRequestDTO.getShippingPrice())
                .itemImage(itemImageUrl)
                .client(client)
                .build();

        itemRepository.save(item);
    }

    // 상품 삭제
    @Override
    @Transactional
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemException(ItemExceptionResponseCode.ITEM_NOT_FOUND,"해당 상품을 찾을 수 없습니다."));

        // S3에서 이미지 삭제 (defaultImage가 아닌 경우에만)
        String imageUrl = item.getItemImage();
        if (imageUrl != null && !imageUrl.endsWith("/defaultImage")) {
            s3Service.delete(imageUrl);
        }

        // DB에서 상품 삭제
        itemRepository.delete(item);
    }

    // 상품 목록
    @Override
    @Transactional(readOnly = true)
    public List<ItemResponseDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();

        if (items.isEmpty()) {
            throw new ItemException(ItemExceptionResponseCode.ITEM_NOT_FOUND, "해당 상품을 찾을 수 없습니다.");
        }

        return items.stream()
                .map(ItemResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 품목 코드로 조회
    @Override
    @Transactional(readOnly = true)
    public List<ItemResponseDTO> getItemsByCode(String code) {
        List<Item> items = itemRepository.findByCode(code);

        if (items.isEmpty()) {
            throw new ItemException(ItemExceptionResponseCode.ITEM_NOT_FOUND, "해당 코드의 상품을 찾을 수 없습니다.");
        }

        return items.stream()
                .map(ItemResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 품목명으로 조회
    @Override
    @Transactional(readOnly = true)
    public List<ItemResponseDTO> getItemsByName(String name) {
        List<Item> items = itemRepository.findByNameContaining(name);

        if (items.isEmpty()) {
            throw new ItemException(ItemExceptionResponseCode.ITEM_NOT_FOUND, "해당 이름의 상품을 찾을 수 없습니다.");
        }

        return items.stream()
                .map(ItemResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 거래처명으로 조회
    @Override
    @Transactional(readOnly = true)
    public List<ItemResponseDTO> getItemsByClientName(String clientName) {
        List<Item> items = itemRepository.findByClientNameContaining(clientName);

        if (items.isEmpty()) {
            throw new ItemException(ItemExceptionResponseCode.ITEM_NOT_FOUND, "해당 거래처의 상품을 찾을 수 없습니다.");
        }

        return items.stream()
                .map(ItemResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

}
