package com.godseven.muntour.member.domain.auth;

import com.godseven.muntour.member.domain.Member;
import com.godseven.muntour.member.domain.type.Muntour;
import com.godseven.muntour.member.domain.MemberRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import org.springframework.http.HttpStatus;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 비밀번호를 SHA-256으로 암호화하는 메소드
    private String encodePassword(String password) {
        return DigestUtils.sha256Hex(password);
    }

    // 비밀번호와 암호화된 비밀번호가 일치하는지 검증하는 메소드
    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return encodePassword(rawPassword).equals(encodedPassword);
    }

    // 회원가입 처리
    public Member registerMember(String id, String password, String nickname, Muntour muntourType, String imageUrl, String imageFolder, String imageName) {
        // ID 중복 확인
        if (memberRepository.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID already exists");
        }

        // 비밀번호 암호화
        String encodedPassword = encodePassword(password);

        // 회원 엔티티 생성 및 저장
        Member member = Member.builder()
                .id(id)
                .password(encodedPassword)
                .nickname(nickname)
                .muntourType(muntourType)
                .imageUrl(imageUrl)
                .imageFolder(imageFolder)
                .imageName(imageName)
                .build();

        return memberRepository.save(member);
    }

    // 로그인 처리
    public Optional<Member> authenticateMember(String id, String password) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent() && matchesPassword(password, member.get().getPassword())) {
            return member;
        }
        return Optional.empty();
    }
}
