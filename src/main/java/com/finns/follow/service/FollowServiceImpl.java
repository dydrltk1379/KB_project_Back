package com.finns.follow.service;

import com.finns.follow.dto.FollowDTO;
import com.finns.follow.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowMapper followMapper;

    /**
     * 사용자가 다른 사용자를 팔로우한다.
     * @param followDTO 팔로우 정보를 담은 DTO
     */
    @Transactional
    @Override
    public void follow(FollowDTO followDTO) {
        followMapper.insertFollow(followDTO);
    }

    /**
     * 사용자가 다른 사용자를 언팔로우한다.
     * @param followDTO 팔로우 정보를 담은 DTO
     */
    @Transactional
    @Override
    public void unfollow(FollowDTO followDTO) {
        followMapper.deleteFollow(followDTO);
    }

    /**
     * 특정 사용자의 팔로잉 목록을 가져온다.
     * @param user_no 사용자의 번호
     * @return 팔로잉 사용자 번호 목록
     */
    @Override
    public List<Integer> getFollowingList(int user_no) {
        return followMapper.selectFollowingList(user_no);
    }

    /**
     * 특정 사용자의 팔로워 목록을 가져온다.
     * @param user_no 사용자의 번호
     * @return 팔로워 사용자 번호 목록
     */
    @Override
    public List<Integer> getFollowerList(int user_no) {
        return followMapper.selectFollowerList(user_no);
    }
}
