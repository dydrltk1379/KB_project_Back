package com.finns.follow.mapper;

import com.finns.follow.dto.FollowDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FollowMapper {
    void insertFollow(FollowDTO followDTO);
    void deleteFollow(FollowDTO followDTO);
    List<Integer> selectFollowingList(@Param("user_no") int user_no);
    List<Integer> selectFollowerList(@Param("user_no") int user_no);
    int checkFollowExists(@Param("user_no") int user_no, @Param("to_user_no") int to_user_no);
}
