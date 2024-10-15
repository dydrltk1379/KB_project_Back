package com.finns.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class MemberServiceImpl implements MemberService {
    final PasswordEncoder passwordEncoder;
    final MemberMapper mapper;

    @Override
    public boolean checkDuplicate(String username) {
        MemberVO member = mapper.checkUsername(username);
        return member != null;
    }

    @Override
    public MemberDTO get(String username) {
        MemberVO member = Optional.ofNullable(mapper.get(username))
                .orElseThrow(() -> new NoSuchElementException("회원 정보를 찾을 수 없습니다."));
        return MemberDTO.of(member);
    }

    private String saveAvatar(MultipartFile avatar, String username) {
        // 아바타 업로드
        if (avatar != null && !avatar.isEmpty()) {
            // 파일 확장자 가져오기
            String originalFilename = avatar.getOriginalFilename();
            String extension = "";

            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase(); // 확장자 추출
            }

            // 지원하는 확장자 확인 (png 또는 jpg만 허용)
            if (!extension.equals(".png") && !extension.equals(".jpg") && !extension.equals(".jpeg")) {
                log.error("지원하지 않는 파일 형식입니다: {}", extension);
                throw new RuntimeException("지원하지 않는 파일 형식입니다. PNG 또는 JPG 파일만 업로드할 수 있습니다.");
            }

            // 파일 저장 경로 설정
            File uploadDir = new File("c:/upload/avatar");

            // 디렉토리가 존재하지 않으면 생성
            if (!uploadDir.exists()) {
                boolean created = uploadDir.mkdirs();
                if (!created) {
                    log.error("아바타 파일 저장 경로를 생성할 수 없습니다.");
                    throw new RuntimeException("아바타 파일 저장 경로를 생성할 수 없습니다.");
                }
            }

            // 저장할 파일 경로 설정 (확장자를 유지)
            File dest = new File(uploadDir, username + extension);
            try {
                avatar.transferTo(dest);  // 파일 저장
                return "/upload/avatar/" + username + extension;  // img_url 경로 반환
            } catch (IOException e) {
                log.error("아바타 파일 저장 중 오류 발생: {}", e.getMessage());
                throw new RuntimeException("아바타 파일 저장 중 오류가 발생했습니다.", e);
            }
        }
        return null; // 아바타가 없을 경우 null 반환
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

    @Transactional
    @Override
    public MemberDTO update(MemberUpdateDTO member) {
        // 기존 회원 정보 가져오기
        MemberVO vo = Optional.ofNullable(mapper.get(member.getUsername()))
                .orElseThrow(() -> new NoSuchElementException("회원 정보를 찾을 수 없습니다."));

        // 현재 비밀번호 확인 (비밀번호가 변경되는 경우에만 확인)
        if (member.getOldPassword() != null && !member.getOldPassword().isEmpty()
                && !passwordEncoder.matches(member.getOldPassword(), vo.getPassword())) {
            throw new PasswordMissmatchException("현재 비밀번호가 일치하지 않습니다.");
        }

        // 새로운 비밀번호가 존재하면 암호화된 비밀번호 사용, 그렇지 않으면 기존 비밀번호 사용
        String encodedPassword = (member.getNewPassword() != null && !member.getNewPassword().isEmpty())
                ? passwordEncoder.encode(member.getNewPassword()) // 비밀번호를 암호화
                : vo.getPassword(); // 기존 비밀번호 유지

        // 업데이트할 `MemberVO` 객체 생성
        MemberVO updatedVO = member.toVO(encodedPassword);

        // 데이터베이스 업데이트
        mapper.update(updatedVO);  // 암호화된 비밀번호 전달

        // 아바타 업데이트
        saveAvatar(member.getAvatar(), member.getUsername());

        return get(member.getUsername());
    }


}