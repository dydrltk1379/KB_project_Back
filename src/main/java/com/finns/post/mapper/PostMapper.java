package com.finns.post.mapper;

import com.finns.post.dto.UpdateAmountDTO;
import com.finns.post.dto.ChangeCountDTO;
import com.finns.post.dto.ChangeRenewStatusDTO;
import com.finns.post.dto.Post;
import com.finns.post.dto.PostRequestDTO;

import java.util.List;

public interface PostMapper {
    // 게시글 하나 가져오기
    Post selectOne(Long no);

    // 게시글들 가져오기(특정 유저, 특정 날짜, 공개 범위)
    List<Post> selectAllByUserAndDateAndIsPublic(PostRequestDTO postRequestDTO);

    // 갱신 버튼 클릭 시(멤버의 마지막 갱신날짜 이후 ~ 현재까지의 게시글들의 renew_status를 true로 수정)
    void updateRenewStatusByUser(ChangeRenewStatusDTO changeRenewStatusDTO);

    List<UpdateAmountDTO> selectUpdatedRenewPost(ChangeRenewStatusDTO changeRenewStatusDTO);

    long selectCountByUser(Long userNo);

    void updatePublicStatus(Long no);



    long updateCount(ChangeCountDTO changeCountDTO);
    long updatePost(Post post);     // 공개 여부, 갱신 여부, 메모에 대해서만 update할 수 있으므로 분리?

}
