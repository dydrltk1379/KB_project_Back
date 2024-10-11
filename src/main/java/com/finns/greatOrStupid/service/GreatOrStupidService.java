package com.finns.greatOrStupid.service;

import com.finns.greatOrStupid.dto.GreatOrStupid;
import com.finns.greatOrStupid.dto.UpdateGreatOrStupidRequestDTO;
import com.finns.greatOrStupid.dto.UpdateGreatOrStupidResponseDTO;
import com.finns.greatOrStupid.mapper.GreatOrStupidMapper;
import com.finns.post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
@Transactional(readOnly = true)
public class GreatOrStupidService {

    private final GreatOrStupidMapper greatOrStupidMapper;
    private final PostMapper postMapper;

    @Transactional
    public void toggleGreat(UpdateGreatOrStupidRequestDTO updateGreatOrStupidRequestDTO) {
        Long userNo = updateGreatOrStupidRequestDTO.getUserNo();
        Long postNo = updateGreatOrStupidRequestDTO.getPostNo();
        boolean isGreat = updateGreatOrStupidRequestDTO.isGreatOrStupid();

        // 현재 isGreat 값을 조회
        Boolean currentIsGreat = greatOrStupidMapper.findByUserNoAndPostNo(userNo, postNo);
        if (currentIsGreat == null) {
            // 현재 데이터가 없을 때 (즉, 처음으로 좋아요 또는 싫어요를 누를 때)
            // isLike가 true면 isGreat를 true로, false면 isGreat를 false로 삽입

            GreatOrStupid newEntry = new GreatOrStupid(userNo, postNo, isGreat);
            greatOrStupidMapper.insert(newEntry);

            if (isGreat) {
                postMapper.incrementGreatCount(postNo); // 좋아요 카운트 증가
            } else {
                postMapper.incrementStupidCount(postNo); // 싫어요 카운트 증가
            }
        } else if (!currentIsGreat) {
            postMapper.decrementStupidCount(postNo); // 싫어요 카운트 감소

            if(isGreat){
                // isGreat가 false였고, 좋아요 버튼을 누른 경우
                greatOrStupidMapper.updateIsGreat(userNo, postNo, true);
                postMapper.incrementGreatCount(postNo); // 좋아요 카운트 증가
            } else{
                // isGreat가 false였고, 싫어요 버튼을 다시 눌렀을 경우 싫어요 삭제
                greatOrStupidMapper.deleteByUserNoAndPostNo(userNo, postNo);
            }
        }
        else {
            postMapper.decrementGreatCount(postNo); // 좋아요 카운트 감소

            if(isGreat){
                // isGreat가 true였고, 좋아요 버튼을 다시 눌렀을 경우 좋아요 삭제
                greatOrStupidMapper.deleteByUserNoAndPostNo(userNo, postNo);
            } else {
                // isGreat가 true였고, 싫어요 버튼을 누른 경우
                greatOrStupidMapper.updateIsGreat(userNo, postNo, false);
                postMapper.incrementStupidCount(postNo); // 싫어요 카운트 증가
            }
        }
    }

    public Boolean isGreat(Long userNo, Long postNo) {
        return greatOrStupidMapper.findByUserNoAndPostNo(userNo, postNo);
    }
}
