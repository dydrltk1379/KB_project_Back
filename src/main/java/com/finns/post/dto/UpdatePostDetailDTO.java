package com.finns.post.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdatePostDetailDTO {
    private long postNo;
    private boolean publicStatus;
    private String category;
    private String memo;
}
