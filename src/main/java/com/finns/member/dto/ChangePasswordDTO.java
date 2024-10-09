package com.finns.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDTO {
    private int user_no;
    private String username;
    private String password;
    private Date birth;
    private String mbti_no;
    private String img_url;
    String oldPassword; // 이전 비밀번호
    String newPassword; // 새 비밀번호
}