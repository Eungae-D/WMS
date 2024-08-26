package com.wms.domain.lot.service;

import com.wms.domain.item.entity.Item;
import com.wms.domain.lot.entity.Lot;

public interface LotService {

    //로트 생성
    Lot createLot(Item item);
}
