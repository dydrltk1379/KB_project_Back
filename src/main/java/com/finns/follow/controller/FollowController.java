package com.finns.follow.controller;

import com.finns.follow.dto.FollowDTO;
import com.finns.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    /**
     * 새로운 팔로우 관계를 추가한다.
     * @param followDTO 팔로우 정보
     * @return 성공 메시지
     */
    @PostMapping
    public ResponseEntity<String> follow(@RequestBody FollowDTO followDTO) {
        followService.follow(followDTO);
        return ResponseEntity.ok("Followed successfully");
    }

    /**
     * 팔로우 관계를 삭제한다 (언팔로우).
     * @param followDTO 팔로우 정보
     * @return 성공 메시지
     */
    @DeleteMapping
    public ResponseEntity<String> unfollow(@RequestBody FollowDTO followDTO) {
        followService.unfollow(followDTO);
        return ResponseEntity.ok("Unfollowed successfully");
    }

    /**
     * 사용자가 팔로잉하고 있는 사용자 목록을 가져온다.
     * @param user_no 사용자 번호
     * @return 팔로잉 사용자 번호 목록
     */
    @GetMapping("/following/{user_no}")
    public ResponseEntity<List<Integer>> getFollowingList(@PathVariable int user_no) {
        List<Integer> followingList = followService.getFollowingList(user_no);
        return ResponseEntity.ok(followingList);
    }

    /**
     * 사용자를 팔로우하는 사용자 목록을 가져온다.
     * @param user_no 사용자 번호
     * @return 팔로워 사용자 번호 목록
     */
    @GetMapping("/followers/{user_no}")
    public ResponseEntity<List<Integer>> getFollowerList(@PathVariable int user_no) {
        List<Integer> followerList = followService.getFollowerList(user_no);
        return ResponseEntity.ok(followerList);
    }
}
