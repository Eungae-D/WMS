package com.wms.domain.inputWarehouseDetail.dto.request;

import com.wms.domain.area.entity.Area;
import com.wms.domain.cell.entity.Cell;
import com.wms.domain.inputWarehouse.entity.InputWarehouse;
import com.wms.domain.inputWarehouseDetail.entity.InputWarehouseDetail;
import com.wms.domain.inputWarehouseDetail.entity.Status;
import com.wms.domain.item.entity.Item;
import com.wms.domain.lot.entity.Lot;
import com.wms.domain.purchaseDetail.entity.PurchaseDetail;
import com.wms.domain.rack.entity.Rack;
import com.wms.domain.warehouse.entity.Warehouse;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InputWarehouseDetailRequestDTO {
    @NotNull(message = "품목 ID는 필수입니다.")
    private Long itemId;

    @NotNull(message = "입고 수량은 필수입니다.")
    private Long amount;

    @NotNull(message = "창고 ID는 필수입니다.")
    private Long warehouseId;

    @NotNull(message = "로트 ID는 필수입니다.")
    private Long lotId;

    @NotNull(message = "발주 상세 ID는 필수입니다.")
    private Long purchaseDetailId;

    @NotNull(message = "구역 ID는 필수입니다.")
    private Long areaId;

    @NotNull(message = "랙 ID는 필수입니다.")
    private Long rackId;

    @NotNull(message = "셀 ID는 필수입니다.")
    private Long cellId;

    // InputWarehouseDetail 엔티티로 변환하는 메서드
    public InputWarehouseDetail toEntity(InputWarehouse inputWarehouse, Item item, Warehouse warehouse, Lot lot, PurchaseDetail purchaseDetail, Area area, Rack rack, Cell cell) {
        return InputWarehouseDetail.builder()
                .status(Status.PURCHASE)
                .amount(amount)
                .lot(lot)
                .warehouse(warehouse)
                .item(item)
                .purchaseDetail(purchaseDetail)
                .inputWarehouse(inputWarehouse)
                .area(area)
                .rack(rack)
                .cell(cell)
                .build();
    }
}
