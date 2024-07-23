package com.godseven.muntour.post.repository;

import com.godseven.muntour.post.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Modifying
    @Query("update Posts p set p.view= p.view+1 where p.id=:id")
    int updateView(Long id);
    Page<Posts> findByCategory(String category, Pageable pageable);

    Page<Posts> findByTitleContaining(String keyword, Pageable pageable);

}
