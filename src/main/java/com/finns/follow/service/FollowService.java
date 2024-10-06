package com.finns.follow.service;

import com.finns.follow.dto.FollowDTO;
import java.util.List;

public interface FollowService {

    /**
     * 사용자가 다른 사용자를 팔로우한다.
     * @param followDTO 팔로우 정보를 담은 DTO
     */
    void follow(FollowDTO followDTO);

    /**
     * 사용자가 다른 사용자를 언팔로우한다.
     * @param followDTO 팔로우 정보를 담은 DTO
     */
    void unfollow(FollowDTO followDTO);

    /**
     * 특정 사용자의 팔로잉 목록을 가져온다.
     * @param user_no 사용자의 번호
     * @return 팔로잉 사용자 번호 목록
     */
    List<Integer> getFollowingList(int user_no);

    /**
     * 특정 사용자의 팔로워 목록을 가져온다.
     * @param user_no 사용자의 번호
     * @return 팔로워 사용자 번호 목록
     */
    List<Integer> getFollowerList(int user_no);
}
