package com.godseven.muntour.post.repository;

import com.godseven.muntour.member.domain.Member;
import com.godseven.muntour.post.entity.Heart;
import com.godseven.muntour.post.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByMemberAndPosts(Member member, Posts posts);
    void deleteByMemberAndPosts(Member member, Posts posts);
    long countByPosts(Posts posts);
}
