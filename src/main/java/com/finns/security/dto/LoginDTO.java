package com.finns.security.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDTO {
    private int user_no;
    private String username;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 날짜 형식 지정
    private Date birth;
    private String mbti_name;
    private String img_url;
    private Date renew_time;

    public static LoginDTO of(HttpServletRequest request) throws AuthenticationException {
        ObjectMapper om = new ObjectMapper();
        try {
            String requestBody = new String(request.getInputStream().readAllBytes());
            System.out.println("Request Body: " + requestBody); // 로그 추가

            return om.readValue(requestBody, LoginDTO.class); // JSON == 객체
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("username 또는 password가 없습니다.");
        }
    }

}