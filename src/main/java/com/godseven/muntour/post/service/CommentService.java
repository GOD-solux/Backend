package com.godseven.muntour.post.service;

import com.godseven.muntour.post.dto.CommentDto;
import com.godseven.muntour.post.entity.Comment;
import com.godseven.muntour.post.entity.Posts;
import com.godseven.muntour.post.repository.CommentRepository;
import com.godseven.muntour.post.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostsRepository postsRepository;

    /* CREATE */
    @Transactional
    public Long save(Long postId, CommentDto.Request dto) {
        Posts posts = postsRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다. " + postId));

        dto.setPosts(posts);

        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return comment.getCommentId();
    }

    /* READ */
    @Transactional(readOnly = true)
    public List<CommentDto.Response> findAll(Long postId) {
        Posts posts = postsRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + postId));
        List<Comment> comments = posts.getComments();
        return comments.stream().map(CommentDto.Response::new).collect(Collectors.toList());
    }

    /* UPDATE */
    @Transactional
    public void update(Long postId, Long commentId, CommentDto.Request dto) {
        Comment comment = commentRepository.findByPostsIdAndId(postId, commentId).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + commentId));
        comment.update(dto.getContent());
    }

    /* DELETE */
    @Transactional
    public void delete(Long postId, Long commentId) {
        Comment comment = commentRepository.findByPostsIdAndId(postId, commentId).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + commentId));
        commentRepository.delete(comment);
    }
}
