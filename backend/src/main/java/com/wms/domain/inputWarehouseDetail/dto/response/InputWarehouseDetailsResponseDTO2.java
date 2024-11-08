package com.wms.domain.inputWarehouseDetail.dto.response;

import com.wms.domain.inputWarehouseDetail.entity.InputWarehouseDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class InputWarehouseDetailsResponseDTO2 {
    private Long inputWarehouseDetailId; //입고 상세 번호
    private Long purchaseSheetId;     // 발주서 번호
    private Long purchaseDetailId;    // 발주서 상세 번호
    private String lotCode;           // 로트 코드
    private String itemCode;          // 품목 코드
    private String itemName;          // 품목명
    private String warehouseName;     // 창고명
    private String areaName;          // 구역명
    private String rackName;          // 랙명
    private String cellName;          // 셀명
    private Long amount;              // 입고량

    public static InputWarehouseDetailsResponseDTO2 fromEntity(InputWarehouseDetail detail) {
        return InputWarehouseDetailsResponseDTO2.builder()
                .inputWarehouseDetailId(detail.getId()) // 입고 상세 번호
                .purchaseSheetId(detail.getInputWarehouse().getPurchaseSheet().getId()) // 발주서 번호
                .purchaseDetailId(detail.getPurchaseDetail().getId())  //발주서 상세 번호
                .lotCode(detail.getLot().getLotNumber())  // 로트 코드
                .itemCode(detail.getItem().getCode())  // 품목 코드
                .itemName(detail.getItem().getName())  // 품목명
                .warehouseName(detail.getWarehouse().getName())  // 창고명
                .areaName(detail.getArea().getName())  // 구역명
                .rackName(detail.getRack().getName())  // 랙명
                .cellName(detail.getCell().getName())  // 셀명
                .amount(detail.getAmount())  // 입고량
                .build();
    }

}
