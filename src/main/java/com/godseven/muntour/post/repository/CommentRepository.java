package com.godseven.muntour.post.repository;

import com.godseven.muntour.post.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByBoardId(int boardId);
}
