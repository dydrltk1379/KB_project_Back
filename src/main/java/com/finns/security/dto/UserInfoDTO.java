package com.finns.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.finns.security.account.domain.MemberVO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    String username;
    List<String> roles;

    public static UserInfoDTO of(MemberVO member) {
        return new UserInfoDTO
                (
                        member.getUsername(),
                        member.getAuthList().stream()
                                .map(a -> a.getAuthority())
                                .toList()
                );
    }
}