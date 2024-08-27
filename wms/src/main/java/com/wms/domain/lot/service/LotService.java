package com.wms.domain.lot.service;

import com.wms.domain.item.entity.Item;
import com.wms.domain.lot.dto.response.LotResponseDTO;
import com.wms.domain.lot.entity.Lot;

import java.util.List;

public interface LotService {

    //로트 생성
    Lot createLot(Item item);

    // 로트 목록 가져오기
    List<LotResponseDTO> getAllLots();

    // 로트 삭제
    void deleteLot(Long lotId);
}
