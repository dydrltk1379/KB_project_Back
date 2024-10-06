package com.finns.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.finns.member.dto.ChangePasswordDTO;
import com.finns.member.dto.MemberDTO;
import com.finns.member.dto.MemberJoinDTO;
import com.finns.member.dto.MemberUpdateDTO;
import com.finns.member.exception.PasswordMissmatchException;
import com.finns.member.mapper.MemberMapper;
import com.finns.security.account.domain.AuthVO;
import com.finns.security.account.domain.MemberVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    final PasswordEncoder passwordEncoder;
    final MemberMapper mapper;

    @Override
    public boolean checkDuplicate(String username) {
        MemberVO member = mapper.checkUsername(username);
        return member != null ? true : false;
    }

    @Override
    public MemberDTO get(String username) {
        MemberVO member = Optional.ofNullable(mapper.get(username))
                .orElseThrow(NoSuchElementException::new);
        return MemberDTO.of(member);
    }
    private void saveAvatar(MultipartFile avatar, String username) {
//아바타 업로드
        if(avatar != null && !avatar.isEmpty()) {
            File dest = new File("c:/upload/avatar", username + ".png");
            try {
                avatar.transferTo(dest);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Transactional
    @Override
    public MemberDTO join(MemberJoinDTO dto) {
        MemberVO member = dto.toVO();
        member.setPassword(passwordEncoder.encode(member.getPassword())); // 비밀번호 암호화
        mapper.insert(member);
        AuthVO authority = new AuthVO();
        authority.setUsername(member.getUsername());
        authority.setAuthority("ROLE_MEMBER");
        mapper.insertAuth(authority);
        saveAvatar(dto.getAvatar(), member.getUsername());
        return get(member.getUsername());
    }

    @Override
    public MemberDTO update(MemberUpdateDTO member) {
        // 기존 회원 정보 가져오기
        MemberVO vo = mapper.get(member.getUsername());

        // 현재 비밀번호 확인
        if (!passwordEncoder.matches(member.getPassword(), vo.getPassword())) {
            throw new PasswordMissmatchException(); // 비밀번호 불일치 예외 처리
        }

        // 새로운 비밀번호가 존재하면 암호화된 비밀번호 사용, 그렇지 않으면 기존 비밀번호 사용
        String encodedPassword = (member.getNewPassword() != null && !member.getNewPassword().isEmpty())
                ? passwordEncoder.encode(member.getNewPassword())
                : vo.getPassword(); // 기존 비밀번호 유지

        // 업데이트할 `MemberVO` 객체 생성
        MemberVO updatedVO = member.toVO(encodedPassword);

        // 데이터베이스 업데이트
        mapper.update(updatedVO);

        // 아바타 업데이트
        saveAvatar(member.getAvatar(), member.getUsername());

        return get(member.getUsername());
    }



//    @Override
//    public void changePassword(ChangePasswordDTO changePassword) {
//        MemberVO member = mapper.get(changePassword.getUsername());
//        if(!passwordEncoder.matches(changePassword.getOldPassword(), member.getPassword())) {
//            throw new PasswordMissmatchException();
//        }
//        changePassword.setNewPassword(passwordEncoder.encode(changePassword.getNewPassword()));
//        mapper.updatePassword(changePassword);
//    }
}