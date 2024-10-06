package com.finns.post.service;

import com.finns.amountByDate.dto.UpdateAmountDTO;
import com.finns.amountByDate.mapper.AmountByDateMapper;
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

    private final PostMapper postMapper;

    private final AmountByDateMapper amountByDateMapper;

    public Post getPost(Long no) {
        return Optional.ofNullable(postMapper.selectOne(no))
                        .orElseThrow(NoSuchElementException::new);
    }

    public List<Post> getPostsByUserAndDateAndIsPublic(PostRequestDTO postRequestDTO) {
        return Optional.ofNullable(postMapper.selectAllByUserAndDateAndIsPublic(postRequestDTO))
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public void updateRenewStatusAndAmount(ChangeRenewStatusDTO changeRenewStatusDTO) {
        // 1. renew_status 업데이트
        postMapper.updateRenewStatusByUser(changeRenewStatusDTO);

        // 2. 갱신된 post의 date, userNo, amount 가져오기
        List<UpdateAmountDTO> updatedPosts = postMapper.selectUpdatedRenewPost(changeRenewStatusDTO);

        // 3. 갱신된 각 데이터에 대해 amount 업데이트
        for (UpdateAmountDTO post : updatedPosts) {
            amountByDateMapper.updateByDateAndUser(post);
        }
    }

    public Long getCountByUser(Long userNo) {
        return Optional.of(postMapper.selectCountByUser(userNo))
                .orElseThrow(NoSuchElementException::new);
    }

}
