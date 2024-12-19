package com.wms.domain.sell.service.impl;

import com.wms.domain.sell.dto.response.SellResponseDTO;
import com.wms.domain.sell.entity.Sell;
import com.wms.domain.sell.repository.SellRepository;
import com.wms.domain.sell.service.SellService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SellServiceImpl implements SellService {

    private final SellRepository sellRepository;

    // 판매 목록 가져오기
    @Override
    @Transactional(readOnly = true)
    public List<SellResponseDTO> getAllSells() {
        List<Sell> sells = sellRepository.findAllSellsWithDetails();
        return sells.stream()
                .map(SellResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
