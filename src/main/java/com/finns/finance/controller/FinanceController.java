package com.finns.finance.controller;

import com.finns.finance.dto.Finance;
import com.finns.finance.dto.FinanceCount;
import com.finns.finance.dto.FinanceDTO;
import com.finns.finance.service.FinanceService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(value = "FinanceController", tags = "금융상품 정보")
public class FinanceController {

    private final FinanceService financeService;

    // 금융 상품 리스트를 반환하는 API
    @GetMapping("/product/{financeProductType}")
    public ResponseEntity<List<FinanceDTO>> getProductList(@PathVariable("financeProductType") String financeProductType) {
        if ("01".equals(financeProductType)) {
            return ResponseEntity.ok(financeService.getDepositList());
        } else if ("02".equals(financeProductType)) {
            return ResponseEntity.ok(financeService.getinstallList());
        } else {
            return ResponseEntity.badRequest()
                    .body(null);
        }
    }
    // 특정 금융 상품 조회 API
    @GetMapping("/product/no/{financeProductNo}")
    public FinanceDTO getFinanceProduct(@PathVariable("financeProductNo") Long financeProductNo) {
        return financeService.getinstallList(financeProductNo);
    }

    // 예금(01)의 가장 높은 금리를 가진 항목 반환
    @GetMapping("/product/highest/deposit")
    public FinanceDTO getHighestIntrRateForDeposit() {
        return financeService.getHighestIntrRateForDeposit();
    }

    // 적금(02)의 가장 높은 금리를 가진 항목 반환
    @GetMapping("/product/highest/savings")
    public FinanceDTO getHighestIntrRateForSavings() {
        return financeService.getHighestIntrRateForSavings();
    }

    // 사용자가 가장 많은 예금(01)
    @GetMapping("/product/top/deposit")
    public FinanceCount getTopDepositByUsers() {
        return financeService.getTopDepositProductByUsers();
    }
    //사용자가 가장 많은 적금(01)
    @GetMapping("/product/top/savings")
    public FinanceCount getTopSavingsByUsers() {
        return financeService.getTopSavingsProductByUsers();
    }
}
