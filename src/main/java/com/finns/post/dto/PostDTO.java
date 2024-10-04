package com.finns.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private BigInteger recordNo;
    private BigInteger userNo;
    private String category;
    private boolean publicStatus;
    private String imageUrl; // 이미지 URL
}
