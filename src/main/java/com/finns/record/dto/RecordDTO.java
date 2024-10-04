package com.finns.record.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class RecordDTO {

    private BigInteger recordNo;
    private BigInteger userNo;
    private boolean publicStatus;
    private String category;
    private String memo;
    // Add any other fields you want to display
}
