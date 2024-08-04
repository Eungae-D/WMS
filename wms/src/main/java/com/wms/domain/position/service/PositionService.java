package com.wms.domain.position.service;

import com.wms.domain.department.dto.request.PositionRequestDTO;
import jakarta.validation.Valid;

public interface PositionService {
    void registerPosition(@Valid PositionRequestDTO positionRequestDTO);
}
