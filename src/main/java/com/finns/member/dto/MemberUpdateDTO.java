package com.finns.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.finns.security.account.domain.MemberVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateDTO {
    private int user_no;
    private String username;
    private String password; // 현재 비밀번호
    private String newPassword; // 새로운 비밀번호 (네이밍 변경)
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 날짜 형식 지정
    private Date birth;
    private String mbti_name;
    private String img_url;
    private Date renew_time;

    private MultipartFile avatar; // 접근 제어자 추가

    // `toVO()` 메서드에서 `newPassword`가 존재할 경우 설정
    public MemberVO toVO(String encodedPassword) {
        // 만약 새 비밀번호가 없다면 기존 비밀번호를 유지
        String finalPassword = (encodedPassword != null && !encodedPassword.isEmpty()) ? encodedPassword : this.password;

        return MemberVO.builder()
                .user_no(user_no)
                .username(username)
                .password(finalPassword) // 암호화된 비밀번호 사용 또는 기존 비밀번호 유지
                .birth(birth)
                .mbti_name(mbti_name)
                .img_url(img_url)
                .renew_time(renew_time)
                .authList(null) // 권한 정보는 필요에 따라 설정
                .build();
    }
}
