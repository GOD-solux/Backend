package com.godseven.muntour.post.controller;

import com.godseven.muntour.auth.MemberRepository;
import com.godseven.muntour.member.domain.Member;
import com.godseven.muntour.post.repository.LikeRepository;
import com.godseven.muntour.post.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikeService likeService;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;

//    @PostMapping("/boards/{boardId}/like")
//    public ResponseEntity<String> addLike(@PathVariable Long boardId, Authentication authentication) {
//        Member member = getAuthenticatedMember(authentication);
//        likeService.addLike(boardId, member);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Like added");
//    }
//
//    @DeleteMapping("/boards/{boardId}/like")
//    public ResponseEntity<String> removeLike(@PathVariable Long boardId, Authentication authentication) {
//        Member member = getAuthenticatedMember(authentication);
//        likeService.removeLike(boardId, member);
//        return ResponseEntity.status(HttpStatus.OK).body("Like removed");
//    }

    @GetMapping("/boards/{boardId}/likes/count")
    public ResponseEntity<Long> getLikeCount(@PathVariable Long boardId) {
        long likeCount = likeService.getLikeCount(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(likeCount);
    }


//    // 인증된 사용자 정보를 가져오는 메서드
//    private Member getAuthenticatedMember(Authentication authentication) {
//        String nickname = getNicknameFromAuthentication(authentication);
//        return memberRepository.findByNickname(nickname).orElseThrow(() -> new RuntimeException("Member not found"));
//    }
//
//    private String getNicknameFromAuthentication(Authentication authentication) {
//        if (authentication.getPrincipal() instanceof UserDetails) {
//            return ((UserDetails) authentication.getPrincipal()).getUsername(); // 여기서 username은 실제로 닉네임으로 매핑됩니다.
//        } else {
//            return authentication.getPrincipal().toString();
//        }
//    }


    @PostMapping("/boards/{boardId}/like")
    public ResponseEntity<String> addLike(@PathVariable Long boardId) {
        Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        likeService.addLike(boardId, member);
        return ResponseEntity.status(HttpStatus.CREATED).body("Like added");
    }

    @DeleteMapping("/boards/{boardId}/like")
    public ResponseEntity<String> removeLike(@PathVariable Long boardId) {
        Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        likeService.removeLike(boardId, member);
        return ResponseEntity.status(HttpStatus.OK).body("Like removed");
    }
}
