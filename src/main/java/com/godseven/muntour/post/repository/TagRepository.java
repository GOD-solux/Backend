package com.godseven.muntour.post.repository;

import com.godseven.muntour.post.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByWord(String word);
}
