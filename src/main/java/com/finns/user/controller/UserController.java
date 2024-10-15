package com.finns.user.controller;

import com.finns.user.dto.*;
import com.finns.user.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(value = "AmountByDateController", tags = "날짜별 총 소비 정보")
@PropertySource({"classpath:/application.properties"})
@CrossOrigin(origins = "http://localhost:5173") // 클라이언트의 도메인을 허용
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{no}")
    public ResponseEntity<User> UserInfo(@PathVariable("no") Long userNo) {
        User user = userService.getUser(userNo);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/users/search/{no}")
    public ResponseEntity<List<SearchUserDTO>> SearchUsers(@PathVariable("no") Long userNo) {
        List<SearchUserDTO> users = userService.getUsers(userNo);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/users/{no}/recommend5")
    public ResponseEntity<List<UserRecommendResponseDTO>> recommend5Users(@PathVariable("no") Long userNo) {
        User user = userService.getUser(userNo);
        UserRecommendRequestDTO userRecommendRequestDTO = new UserRecommendRequestDTO(userNo, user.getMbtiName());

        List<UserRecommendResponseDTO> recommendUsers = userService.getRecommend5ByMbti(userRecommendRequestDTO);
        return ResponseEntity.ok().body(recommendUsers);
    }

    @PutMapping("/users/{no}/mbti")
    public ResponseEntity<?> analysisMbti(@PathVariable("no") Long userNo) {
        userService.setMbtiByCategory(userNo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/top3/{year}/{month}")
    public ResponseEntity<List<UserTop3DTO>> top3ForAmountByDate(@PathVariable("year") int year, @PathVariable("month") int month) {
        YearAndMonthDTO yearAndMonthDTO = new YearAndMonthDTO(year, month);
        List<UserTop3DTO> top3Users = userService.getTop3ForAmountByDate(yearAndMonthDTO);
        return ResponseEntity.ok().body(top3Users);
    }

}
