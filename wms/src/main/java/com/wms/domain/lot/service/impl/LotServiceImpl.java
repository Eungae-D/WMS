package com.wms.domain.lot.service.impl;

import com.wms.domain.item.entity.Item;
import com.wms.domain.lot.dto.response.LotResponseDTO;
import com.wms.domain.lot.entity.Lot;
import com.wms.domain.lot.repository.LotRepository;
import com.wms.domain.lot.service.LotService;
import com.wms.global.exception.exception.LotException;
import com.wms.global.exception.responseCode.LotExceptionResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LotServiceImpl implements LotService {

    private final LotRepository lotRepository;

    // 로트 생성
    @Override
    @Transactional
    public Lot createLot(Item item) {
        LocalDate today = LocalDate.now();  // 현재 날짜 가져오기
        String datePrefix = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        int todayLotCount = lotRepository.countByLotNumberPrefix(datePrefix);  // 오늘 날짜에 생성된 로트 개수 계산

        String lotNumber = Lot.generateLotNumber(today, todayLotCount + 1);  // 오늘 날짜와 현재 개수로 로트 번호 생성

        if (lotRepository.existsByLotNumber(lotNumber)) {
            throw new LotException(LotExceptionResponseCode.LOT_ALREADY_EXISTS, "이미 존재하는 로트 번호입니다.");
        }

        Lot lot = Lot.builder()
                .lotNumber(lotNumber)
                .item(item)
                .build();

        lotRepository.save(lot);

        return lot;
    }

    // 로트 목록 가져오기
    @Override
    @Transactional(readOnly = true)
    public List<LotResponseDTO> getAllLots() {
        List<Lot> lots = lotRepository.findAllWithFetchJoin();

        if (lots.isEmpty()) {
            throw new LotException(LotExceptionResponseCode.LOT_NOT_FOUND, "로트를 찾을 수 없습니다.");
        }

        return lots.stream()
                .map(LotResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 로트 삭제
    @Override
    @Transactional
    public void deleteLot(Long lotId) {
        Lot lot = lotRepository.findById(lotId)
                .orElseThrow(() -> new LotException(LotExceptionResponseCode.LOT_NOT_FOUND, "로트를 찾을 수 없습니다."));

        lotRepository.delete(lot);
    }
}
