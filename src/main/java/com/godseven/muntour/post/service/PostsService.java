package com.godseven.muntour.post.service;

import com.godseven.muntour.post.dto.PostsDto;
import com.godseven.muntour.post.entity.Posts;
import com.godseven.muntour.post.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    /* CREATE */
    @Transactional
    public Long save(PostsDto.Request dto) {
        Posts posts = dto.toEntity();
        postsRepository.save(posts);
        return posts.getPostId();
    }

    /* READ */
    @Transactional(readOnly = true)
    public PostsDto.Response findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));
        return new PostsDto.Response(posts);
    }

    /* UPDATE */
    @Transactional
    public void update(Long id, PostsDto.Request dto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
        posts.update(dto.getTitle(), dto.getContent());
    }

    /* DELETE */
    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
        postsRepository.delete(posts);
    }

    /* Views Counting */
    @Transactional
    public int updateView(Long id) {
        return postsRepository.updateView(id);
    }

    /* Paging and Sort */
    @Transactional(readOnly = true)
    public Page<Posts> pageList(Pageable pageable) {
        return postsRepository.findAll(pageable);
    }

    /* search */
    @Transactional(readOnly = true)
    public Page<Posts> search(String keyword, Pageable pageable) {
        return postsRepository.findByTitleContaining(keyword, (java.awt.print.Pageable) pageable);
    }

    /* Find by Category */
    @Transactional(readOnly = true)
    public Page<Posts> findByCategory(String category, Pageable pageable) {
        return postsRepository.findByCategory(category, (java.awt.print.Pageable) pageable);
    }
}
