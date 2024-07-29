package com.godseven.muntour.member.domain.auth;

import com.godseven.muntour.member.domain.Member;
import com.godseven.muntour.member.domain.type.Muntour;
import com.godseven.muntour.member.domain.MemberRequest;
import com.godseven.muntour.member.service.MemberService;
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
            return new ResponseEntity<>("Member registered successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid Muntour type", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error during registration", HttpStatus.INTERNAL_SERVER_ERROR);
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
}
