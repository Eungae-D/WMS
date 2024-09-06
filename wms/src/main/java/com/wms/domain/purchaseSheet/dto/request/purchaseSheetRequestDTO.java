package com.wms.domain.purchaseSheet.dto.request;

import com.wms.domain.purchaseDetail.dto.request.PurchaseDetailRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class purchaseSheetRequestDTO {

    @NotNull(message = "납기일자는 필수입니다.")
    private LocalDate dueDate;

    @NotNull(message = "수주서 ID는 필수입니다.")
    private Long orderSheetId;

    @NotNull(message = "발주 상세 목록은 필수입니다.")
    private List<PurchaseDetailRequestDTO> purchaseDetails;
}
