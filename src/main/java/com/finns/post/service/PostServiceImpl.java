package com.finns.post.service;

import com.finns.post.dto.PostDTO;
import com.finns.post.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public PostDTO getPostById(Long id) {
        // Post를 가져오는 로직 (Record에 기반하여)
        return postMapper.selectPostById(id);
    }

    @Override
    public void updatePost(Long id, PostDTO postDTO) {
        // 게시물 수정 로직
        postMapper.updatePost(id, postDTO);
    }
}
