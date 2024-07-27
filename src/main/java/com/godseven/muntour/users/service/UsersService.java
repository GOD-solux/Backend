package com.godseven.muntour.users.service;

import com.godseven.muntour.member.domain.Member;
import com.godseven.muntour.users.dto.ImageRequestDto;
import com.godseven.muntour.users.dto.TypeJoinResponseDto;
import com.godseven.muntour.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder encoder;;

//    public UsersService(UsersRepository userRepository) {
//        this.usersRepository = userRepository;
//    }

    // memberId 중복 체크
    @Transactional
    public boolean checkUser(Long memberId) {
        return usersRepository.existsByMemberId(memberId);
    }

    @Transactional
    public boolean resetPassword(Long memberId, String newPassword) {
        Member member = usersRepository.findByMemberId(memberId)
                .orElse(null);

        if (member == null) {
            return false;
        }

        member.setPassword(encoder.encode(newPassword));
        usersRepository.save(member);

        return true;
    }

    @Transactional
    public Long update(ImageRequestDto requestDto) {
        Long memberId = requestDto.getMemberId();
        String imageUrl = requestDto.getImageUrl();

        // 사용자 엔티티 데이터베이스에서 찾기
        Member member = usersRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID: " + memberId));

        // 이미지 URL 업데이트
        member.setImageUrl(imageUrl);

        // 변경 사항 저장
        usersRepository.save(member);

        return member.getMemberId();
    }

    @Transactional
    public Member findByMemberId(Long memberId) {
        return usersRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID: " + memberId));
    }

    @Transactional(readOnly = true)
    public TypeJoinResponseDto getMuntourType(Long memberId) {
        Member member = findByMemberId(memberId);
        return new TypeJoinResponseDto(member.getMuntourType());
    }

}
