package com.finns.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.finns.security.account.domain.MemberVO;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    private int user_no;
    private String username;
    private String password;
    private String birth;
    private String mbti_no;
    private String imgurl;
    List<String> roles;

    public static UserInfoDTO of(MemberVO member) {
        if (member == null) {
            throw new IllegalArgumentException("MemberVO is null");
        }
        System.out.println("MemberVO: " + member); // 로그 추가
        System.out.println("MemberVO username: " + member.getUsername()); // 로그 추가
        return new UserInfoDTO(
                member.getUser_no(),
                member.getUsername(),
                member.getPassword(),
                member.getBirth(),
                member.getMbti_no(),
                member.getImgurl(),
                member.getAuthList().stream()
                        .map(a -> a.getAuthority())
                        .collect(Collectors.toList()) // 변경된 부분
        );
    }
}