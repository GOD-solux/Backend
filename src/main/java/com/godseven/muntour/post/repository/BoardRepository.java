package com.godseven.muntour.post.repository;

import com.godseven.muntour.post.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {


    //검색 기능 구현을 위해
    @Query("SELECT b FROM Board b WHERE b.title LIKE %:keyword% OR b.content LIKE %:keyword%")
    List<Board> searchByTitleOrContent(@Param("keyword") String keyword);
}

