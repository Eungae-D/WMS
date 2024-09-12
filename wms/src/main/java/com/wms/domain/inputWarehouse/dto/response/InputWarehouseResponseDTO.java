package com.wms.domain.inputWarehouse.dto.response;

import com.wms.domain.inputWarehouse.entity.InputWarehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Builder
public class InputWarehouseResponseDTO {

    private Long inputWarehouseId;     // 입고서 ID
    private Long purchaseSheetId;      // 발주서 ID
    private String userName;           // 담당자 이름
    private String clientName;         // 거래처 이름
    private String status;             // 진행 상태
    private LocalDateTime arrivalDateTime; // 입고 시간
    private List<String> itemNames;    // 품목명 리스트

    public static InputWarehouseResponseDTO fromEntity(InputWarehouse inputWarehouse) {

        // 품목명 리스트 생성
        List<String> itemNames = inputWarehouse.getInputWarehouseDetails().stream()
                .map(detail -> detail.getItem().getName())
                .collect(Collectors.toList());

        return InputWarehouseResponseDTO.builder()
                .inputWarehouseId(inputWarehouse.getId())
                .purchaseSheetId(inputWarehouse.getPurchaseSheet().getId())
                .userName(inputWarehouse.getUser().getName())
                .clientName(inputWarehouse.getPurchaseSheet().getClient().getName())
                .status(inputWarehouse.getStatus().name())
                .arrivalDateTime(inputWarehouse.getArrivalDateTime())
                .itemNames(itemNames)
                .build();
    }
}




