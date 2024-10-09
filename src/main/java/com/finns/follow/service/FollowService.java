package com.finns.follow.service;

import com.finns.follow.dto.FollowDTO;
import com.finns.follow.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowService {
    private final FollowMapper followMapper;

    @Transactional
    public void follow(FollowDTO followDTO) {
        followMapper.insertFollow(followDTO);
    }

    @Transactional
    public void unFollow(FollowDTO followDTO) {
        followMapper.deleteFollow(followDTO);
    }


    public List<Integer> getFollowingList(int user_no) {
        return followMapper.selectFollowingList(user_no);
    }

    public List<Integer> getFollowerList(int user_no) {
        return followMapper.selectFollowerList(user_no);
    }


}
