package com.godseven.muntour.post.repository;

import com.godseven.muntour.member.domain.Member;
import com.godseven.muntour.post.entity.Board;
import com.godseven.muntour.post.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByBoardAndMember(Board board, Member member);
    long countByBoard(Board board);
}
