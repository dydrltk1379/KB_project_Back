package com.finns.follow.mapper;

import com.finns.follow.dto.FollowDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {

    /**
     * 팔로우 관계를 추가한다.
     * @param followDTO 팔로우 정보를 담은 DTO
     */
    void insertFollow(FollowDTO followDTO);

    /**
     * 팔로우 관계를 삭제한다 (언팔로우).
     * @param followDTO 팔로우 정보를 담은 DTO
     */
    void deleteFollow(FollowDTO followDTO);

    /**
     * 특정 사용자가 팔로우하고 있는 사용자 목록을 가져온다.
     * @param user_no 사용자 번호
     * @return 팔로잉 사용자 번호 목록
     */
    List<Integer> selectFollowingList(int user_no);

    /**
     * 특정 사용자를 팔로우하는 사용자 목록을 가져온다.
     * @param user_no 사용자 번호
     * @return 팔로워 사용자 번호 목록
     */
    List<Integer> selectFollowerList(int user_no);
}
