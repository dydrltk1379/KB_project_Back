package com.finns.post.controller;

import com.finns.post.dto.ChangeRenewStatusDTO;
import com.finns.post.dto.Post;
import com.finns.post.dto.PostRequestDTO;
import com.finns.post.service.PostService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalTime.now;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(value = "PostController", tags = "게시글 정보")
@PropertySource({"classpath:/application.properties"})
@CrossOrigin(origins = "http://localhost:5173") // 클라이언트의 도메인을 허용
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/{no}")
    public ResponseEntity<Post> postInfo(@PathVariable("no") Long no) {
        Post post = postService.getPost(no);
        return ResponseEntity.ok().body(post);
    }

    @PostMapping("/posts")
    public ResponseEntity<List<Post>> postsInfoByUserAndDateAndIsPublic(@RequestBody PostRequestDTO postRequestDTO) {
        List<Post> posts = postService.getPostsByUserAndDateAndIsPublic(postRequestDTO);
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/users/{no}/renew")
    public ResponseEntity<?> renewPosts(@PathVariable("no") Long userNo) {
        LocalDateTime now = LocalDateTime.now();

        ChangeRenewStatusDTO changeRenewStatusDTO = new ChangeRenewStatusDTO(userNo, now);
        postService.updateRenewStatusAndAmount(changeRenewStatusDTO);

        // user의 renew_time을 변수 now로 업데이트 해줘야함
        // user쪽에서 구현해서 userService로 동작시키자!


        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{no}/posts/count")
    public ResponseEntity<Long> countPosts(@PathVariable("no") Long userNo) {
        Long count = postService.getCountByUser(userNo);
        return ResponseEntity.ok(count);
    }

    @PutMapping("posts/{no}/togglePublicStatus")
    public ResponseEntity<?> togglePublicStatus(@PathVariable("no") Long no) {
        postService.reversePublicStatus(no);
        return ResponseEntity.ok().build();
    }
}
