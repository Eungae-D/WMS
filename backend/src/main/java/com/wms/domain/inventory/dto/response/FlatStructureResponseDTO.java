package com.wms.domain.inventory.dto.response;


import com.wms.domain.area.dto.response.AreaResponseDTO;
import com.wms.domain.cell.dto.response.CellResponseDTO;
import com.wms.domain.rack.dto.response.RackResponseDTO;
import com.wms.domain.warehouse.dto.response.WarehouseResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class FlatStructureResponseDTO {
    private List<WarehouseResponseDTO> warehouses;
    private List<AreaResponseDTO> areas;
    private List<RackResponseDTO> racks;
    private List<CellResponseDTO> cells;
}
