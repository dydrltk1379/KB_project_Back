package com.finns.post.service;

import com.finns.post.dto.ChangeRenewStatusDTO;
import com.finns.post.dto.Post;
import com.finns.post.dto.PostRequestDTO;
import com.finns.post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
@Transactional(readOnly = true)
public class PostService {

    private final PostMapper mapper;

    public Post getPost(Long no) {
        return Optional.ofNullable(mapper.selectOne(no))
                        .orElseThrow(NoSuchElementException::new);
    }

    public List<Post> getPostsByUserAndDateAndIsPublic(PostRequestDTO postRequestDTO) {
        return Optional.ofNullable(mapper.selectAllByUserAndDateAndIsPublic(postRequestDTO))
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public void renewPostsByUser(ChangeRenewStatusDTO changeRenewStatusDTO) {
        mapper.updateRenewStatusByUser(changeRenewStatusDTO);
    }

}
