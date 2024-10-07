package com.finns.amountByDate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAmountDTO {
    private LocalDate transactionDate;
    private Long userNo;
    private Long amount;
}
