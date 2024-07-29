package com.godseven.muntour.post.service;

import com.godseven.muntour.member.domain.Member;
import com.godseven.muntour.post.entity.Board;
import com.godseven.muntour.post.entity.Like;
import com.godseven.muntour.post.repository.BoardRepository;
import com.godseven.muntour.post.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void addLike(Long boardId, Member member) {
        Board board = boardRepository.findById(Math.toIntExact(boardId)).orElseThrow(() -> new RuntimeException("Board not found"));
        likeRepository.findByBoardAndMember(board, member).ifPresent(like -> {
            throw new RuntimeException("Already liked");
        });

        Like like = new Like(board, member);
        likeRepository.save(like);
    }

    @Transactional
    public void removeLike(Long boardId, Member member) {
        Board board = boardRepository.findById(Math.toIntExact(boardId)).orElseThrow(() -> new RuntimeException("Board not found"));
        Like like = likeRepository.findByBoardAndMember(board, member).orElseThrow(() -> new RuntimeException("Like not found"));
        likeRepository.delete(like);
    }

    public long getLikeCount(Long boardId) {
        Board board = boardRepository.findById(Math.toIntExact(boardId)).orElseThrow(() -> new RuntimeException("Board not found"));
        return likeRepository.countByBoard(board);
    }
}