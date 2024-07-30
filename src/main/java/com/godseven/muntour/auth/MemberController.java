package com.godseven.muntour.auth;

import com.godseven.muntour.member.domain.Member;
import com.godseven.muntour.member.domain.type.Muntour;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public ResponseEntity<String> registerMember(@RequestBody MemberRequest memberRequest) {
        if (!memberRequest.getPassword().equals(memberRequest.getConfirmPassword())) {
            return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        try {
            Muntour muntourType = Muntour.valueOf(memberRequest.getMuntourType()); // Muntour 타입으로 변환

            Member member = memberService.registerMember(
                    memberRequest.getId(),
                    memberRequest.getPassword(),
                    memberRequest.getNickname(),
                    muntourType,
                    memberRequest.getImageUrl(),
                    memberRequest.getImageFolder(),
                    memberRequest.getImageName()
            );
            return new ResponseEntity<>("로그인 성공", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("로그인 실패", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("로그인 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 로그인 처리
    @PostMapping("/login")
    public ResponseEntity<String> loginMember(@RequestBody MemberRequest memberRequest) {
        Optional<Member> member = memberService.authenticateMember(memberRequest.getId(), memberRequest.getPassword());
        if (member.isPresent()) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    // 로그아웃 처리
    @PostMapping("/logout")
    public ResponseEntity<String> logoutMember(HttpSession session) {
        session.invalidate(); // 현재 세션 무효화
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }
}
