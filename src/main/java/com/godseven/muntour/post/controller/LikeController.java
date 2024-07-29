package com.godseven.muntour.post.controller;

import com.godseven.muntour.member.domain.Member;
import com.godseven.muntour.post.repository.MemberRepository;
import com.godseven.muntour.post.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikeService likeService;
    private final MemberRepository memberRepository;

    @PostMapping("/boards/{boardId}/like")
    public ResponseEntity<String> addLike(@PathVariable Long boardId) {
        Member member = memberRepository.findById(1).orElseThrow(() -> new RuntimeException("Member not found")); // 수정 필요
        likeService.addLike(boardId, member);
        return ResponseEntity.status(HttpStatus.CREATED).body("Like added");
    }

    @DeleteMapping("/boards/{boardId}/like")
    public ResponseEntity<String> removeLike(@PathVariable Long boardId) {
        Member member = memberRepository.findById(1).orElseThrow(() -> new RuntimeException("Member not found")); // 수정 필요
        likeService.removeLike(boardId, member);
        return ResponseEntity.status(HttpStatus.OK).body("Like removed");
    }

    @GetMapping("/boards/{boardId}/likes/count")
    public ResponseEntity<Long> getLikeCount(@PathVariable Long boardId) {
        long likeCount = likeService.getLikeCount(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(likeCount);
    }
}