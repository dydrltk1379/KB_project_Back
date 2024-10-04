package com.finns.post.mapper;

import com.finns.post.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
    PostDTO selectPostById(Long id);
    void updatePost(Long id, PostDTO postDTO);
}
