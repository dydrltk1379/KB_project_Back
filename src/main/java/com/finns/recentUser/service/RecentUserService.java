package com.finns.recentUser.service;

import com.finns.follow.service.FollowService;
import com.finns.recentUser.dto.InsertRecentUserDTO;
import com.finns.recentUser.dto.RecentUserResponseDTO;
import com.finns.recentUser.mapper.RecentUserMapper;
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
public class RecentUserService {

    private final RecentUserMapper recentUserMapper;
    private final FollowService followService;

    public List<RecentUserResponseDTO> getRecentUser(Long userNo) {
        List<RecentUserResponseDTO> recentUsers = recentUserMapper.selectRecentUser(userNo);
        for (RecentUserResponseDTO recentUser : recentUsers) {
            boolean isFollow = followService.isFollowing(userNo, recentUser.getUserNo());
            recentUser.setFollow(isFollow);
        }

        return Optional.of(recentUsers)
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public void insertRecentUser(InsertRecentUserDTO insertRecentUserDTO) {
        recentUserMapper.insertRecentUser(insertRecentUserDTO);
    }
}
