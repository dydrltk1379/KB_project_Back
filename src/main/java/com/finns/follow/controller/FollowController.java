package com.finns.follow.controller;

import com.finns.follow.dto.FollowDTO;
import com.finns.follow.exception.AlreadyFollowingException;
import com.finns.follow.exception.FollowNotFoundException;
import com.finns.follow.service.FollowService;
import com.finns.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // 클라이언트의 도메인을 허용
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{to_user_no}")
    public ResponseEntity<String> follow(@RequestBody FollowDTO followDTO, @PathVariable int to_user_no) {
        followDTO.setTo_user_no(to_user_no);
        try {
            followService.follow(followDTO);
            return ResponseEntity.ok("Followed successfully");
        } catch (AlreadyFollowingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{to_user_no}")
    public ResponseEntity<String> unfollow(@RequestBody FollowDTO followDTO, @PathVariable int to_user_no) {
        followDTO.setTo_user_no(to_user_no);
        try {
            followService.unfollow(followDTO);
            return ResponseEntity.ok("Unfollowed successfully");
        } catch (FollowNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/following/{user_no}")
    public ResponseEntity<List<MemberDTO>> getFollowingList(@PathVariable int user_no) {
        List<MemberDTO> followingList = followService.getFollowingList(user_no);
        return ResponseEntity.ok(followingList);
    }

    @GetMapping("/followers/{user_no}")
    public ResponseEntity<List<MemberDTO>> getFollowerList(@PathVariable int user_no) {
        List<MemberDTO> followerList = followService.getFollowerList(user_no);
        return ResponseEntity.ok(followerList);
    }






}