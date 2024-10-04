package com.finns.member.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.finns.security.account.domain.MemberVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateDTO {
    private int user_no;
    private String username;
    private String password;
    private String birth;
    private String mbti_no;
    private String imgurl;
    private Date renew_date;

    MultipartFile avatar;

    public MemberVO toVO(){
        return MemberVO.builder()
                .username(username)
                .birth(birth)
                .build();
    }
}
