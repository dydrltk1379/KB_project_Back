package com.finns.comment.service;

import com.finns.comment.dto.CommentDTO;
import com.finns.comment.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
@Transactional(readOnly = true)
public class CommentService {
    private final CommentMapper commentMapper;

    // 피드당 댓글 리스트 조회
    public List<CommentDTO> getCommentList(Long postNo) {
        return commentMapper.getCommentByUser(postNo);
    }
}
