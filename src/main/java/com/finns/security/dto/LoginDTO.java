package com.finns.security.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String birth;
    private String mbti_no;
    private String imgurl;
    private Date renew_date;

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