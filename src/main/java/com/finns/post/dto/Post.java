package com.finns.post.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Post {
    private long postNo;
    private long userNo;
    private long cardNo;
    private boolean publicStatus;
    private String category;
    private String memo;
    private long amount;
    private String place;
    private Date transactionDate;
    private long greatCount;
    private long stupidCount;
    private boolean renewStatus;
}
