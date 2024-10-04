package com.finns.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostVO {
    private BigInteger recordNo;
    private BigInteger userNo;
    private BigInteger cardNo;
    private boolean publicStatus;
    private String category;
    private String memo;
    private BigInteger amount;
    private String place;
    private Date transactionDate;
    private BigInteger greatCount;
    private BigInteger stupidCount;
    private boolean renewStatus;
}