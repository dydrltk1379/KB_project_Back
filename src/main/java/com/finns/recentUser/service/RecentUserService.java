package com.finns.recentUser.service;

import com.finns.recentUser.dto.InsertRecentUserDTO;
import com.finns.recentUser.dto.RecentUserResponseDTO;
import com.finns.recentUser.mapper.RecentUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
@Transactional(readOnly = true)
public class RecentUserService {
    private final RecentUserMapper recentUserMapper;

    public List<RecentUserResponseDTO> getRecentUser(Long userNo) {
        List<RecentUserResponseDTO> recentUsers = recentUserMapper.selectRecentUser(userNo);
        return recentUsers;
    }

    @Transactional
    public void insertRecentUser(InsertRecentUserDTO insertRecentUserDTO) {
        recentUserMapper.insertRecentUser(insertRecentUserDTO);
    }
}
