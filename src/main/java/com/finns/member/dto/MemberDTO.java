package com.finns.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.finns.security.account.domain.MemberVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private int user_no;
    private String username;
    private String password;
    private String birth;
    private String mbti_no;
    private String imgurl;
    private Date renew_date;

    MultipartFile avatar;

    private List<String> authList;

    public static MemberDTO of(MemberVO m) {
        return MemberDTO.builder()
                .username(m.getUsername())
                .authList(m.getAuthList().stream().map(a->a.getAuthority()).toList())
                .build();
    }
    public MemberVO toVO() {
        return MemberVO.builder()
                .username(username)
                .build();
    }
}
