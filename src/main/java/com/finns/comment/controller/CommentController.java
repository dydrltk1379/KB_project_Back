package com.finns.comment.controller;

import com.finns.comment.dto.CommentDTO;
import com.finns.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{post_no}")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostNo(@PathVariable("post_no")Long postNo) {
        return ResponseEntity.ok(commentService.getCommentList(postNo));
    }

}
