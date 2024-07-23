package com.godseven.muntour.post.controller;

import com.godseven.muntour.post.dto.CommentDto;
import com.godseven.muntour.post.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API Controller
 */
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {

    private final CommentService commentService;

    /* CREATE */
    @PostMapping("/posts/{postNumber}/comment")
    public ResponseEntity<Long> save(@PathVariable Long postNumber, @RequestBody CommentDto.Request dto) {
        return ResponseEntity.ok(commentService.save(postNumber, dto));
    }

    /* READ */
    @GetMapping("/posts/{postNumber}/comments")
    public List<CommentDto.Response> read(@PathVariable Long postNumber) {
        return commentService.findAll(postNumber);
    }

    /* UPDATE */
    @PutMapping("/posts/{postNumber}/comments/{commentId}")
    public ResponseEntity<Long> update(@PathVariable Long postNumber, @PathVariable Long commentId, @RequestBody CommentDto.Request dto) {
        commentService.update(postNumber, commentId, dto);
        return ResponseEntity.ok(commentId);
    }

    /* DELETE */
    @DeleteMapping("/posts/{postNumber}/comment/{commentId}")
    public ResponseEntity<Long> delete(@PathVariable Long postNumber, @PathVariable Long commentId) {
        commentService.delete(postNumber, commentId);
        return ResponseEntity.ok(commentId);
    }
}
