package com.finns.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import com.finns.security.account.domain.CustomUser;
import com.finns.security.account.domain.MemberVO;
import com.finns.security.account.mapper.UserDetailsMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j
//@Component
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDetailsMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Loading user by username: " + username);

        MemberVO vo = mapper.get(username);
        if (vo == null) {
            log.error("User not found: " + username);
            throw new UsernameNotFoundException(username + "은 없는 id입니다.");
        }

        log.debug("User found: " + vo.getUsername());

        return new CustomUser(vo); // UserDetails 구현체 반환
    }

}