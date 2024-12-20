package com.wms.domain.sell.dto.response;

import com.wms.domain.sell.entity.Sell;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Builder
public class SellResponseDTO {

    private Long id;                // 판매 번호
    private String userName;        // 담당자 이름
    private String orderSheetName;  // 도착지 이름
    private LocalDate dueDate;      // 출고일
    private String status;          // 출고 상태
    private List<String> itemNames; // 품목 이름

    public static SellResponseDTO fromEntity(Sell sell) {
        List<String> itemNames = sell.getSellDetails().stream()
                .map(detail -> detail.getItem().getName())
                .collect(Collectors.toList());

        return SellResponseDTO.builder()
                .id(sell.getId())
                .userName(sell.getUser().getName())
                .orderSheetName(sell.getOrderSheet().getClient().getName())
                .dueDate(sell.getDueDate())
                .status(sell.getStatus().name())
                .itemNames(itemNames)
                .build();
    }
}
