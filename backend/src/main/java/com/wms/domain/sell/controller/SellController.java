package com.wms.domain.sell.controller;

        import com.wms.domain.sell.service.SellService;
        import com.wms.global.util.response.ApiResponse;
        import jakarta.validation.Valid;
        import lombok.RequiredArgsConstructor;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sell")
public class SellController {

    private final SellService sellService;

//        private final SellService sellService;

//        // 판매 등록
//        @PostMapping("/register")
//        public ResponseEntity<ApiResponse<?>> registerSell(@Valid @RequestBody SellRequestDTO sellRequestDTO) {
//                sellService.registerSell(sellRequestDTO);
//                return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("판매 등록 성공."));
//        }
//
//        // 판매 삭제
//        @DeleteMapping("/delete/{sellId}")
//        public ResponseEntity<ApiResponse<?>> deleteSell(@PathVariable Long sellId) {
//                sellService.deleteSell(sellId);
//                return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccessNoContent("판매 삭제 성공."));
//        }
//
//        // 판매 목록 가져오기
//        @GetMapping("/list")
//        public ResponseEntity<ApiResponse<?>> getAllSells() {
//                List<SellResponseDTO> sellList = sellService.getAllSells();
//                return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(sellList, "판매 목록 가져오기 성공."));
//        }
//
//        // 판매 목록 가져오기(상태로 검색)
//        @GetMapping("/status/{status}")
//        public ResponseEntity<ApiResponse<?>> getSellsByStatus(@PathVariable String status) {
//                List<SellResponseDTO> sellList = sellService.getSellsByStatus(status);
//                return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(sellList, "판매 목록 가져오기 성공.(상태 검색)"));
//        }
//
//        // 판매 상세 정보 가져오기
//        @GetMapping("/details")
//        public ResponseEntity<ApiResponse<?>> getAllSellsWithDetails() {
//                List<SellDetailResponseDTO> sellDetails = sellService.getAllSellsWithDetails();
//                return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(sellDetails, "판매 상세 목록 가져오기 성공."));
//        }

}
