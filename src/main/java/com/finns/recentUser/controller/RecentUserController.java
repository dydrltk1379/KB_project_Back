package com.finns.recentUser.controller;

import com.finns.recentUser.dto.*;
import com.finns.recentUser.service.RecentUserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(value = "AmountByDateController", tags = "날짜별 총 소비 정보")
@PropertySource({"classpath:/application.properties"})
@CrossOrigin(origins = "http://localhost:5173") // 클라이언트의 도메인을 허용
public class RecentUserController {

    private final RecentUserService recentUserService;

    @GetMapping("/users/{no}/recentUser")
    public ResponseEntity<List<RecentUserResponseDTO>> recentUsers(@PathVariable("no") Long userNo) {
        List<RecentUserResponseDTO> recentUsers = recentUserService.getRecentUser(userNo);
        return ResponseEntity.ok().body(recentUsers);
    }

    @PutMapping("/recentUser/{userNo}/{toUserNo}")
    public ResponseEntity<?> newRecentUser(@PathVariable("userNo") Long userNo, @PathVariable("toUserNo") Long toUserNo) {
        LocalDateTime now = LocalDateTime.now();
        InsertRecentUserDTO insertRecentUserDTO = new InsertRecentUserDTO(userNo, toUserNo, now);
        recentUserService.insertRecentUser(insertRecentUserDTO);
        return ResponseEntity.ok().build();
    }
}
