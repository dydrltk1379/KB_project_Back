package com.finns.post.service;

import com.finns.post.dto.PostDTO;

public interface PostService {
    PostDTO getPostById(Long id);
    void updatePost(Long id, PostDTO postDTO);
}
