package com.godseven.muntour.post.dto;

import com.godseven.muntour.member.domain.Member;
import com.godseven.muntour.post.entity.Comment;
import com.godseven.muntour.post.entity.Posts;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private Long commentId;
        private String content;
        private boolean secret;
        private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        private Member member;
        private Posts posts;

        // DTO -> 엔티티
        public Comment toEntity() {
            return Comment.builder()
                    .commentId(commentId)
                    .content(content)
                    .secret(secret)
                    .createdDate(createdDate)
                    .modifiedDate(modifiedDate)
                    .member(member)
                    .posts(posts)
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Long commentId;
        private String content;
        private boolean secret;
        private String createdDate;
        private String modifiedDate;
        private String nickname;
        private Long memberId;
        private Long postsId;

        // 엔티티 -> DTO
        public Response(Comment comment) {
            this.commentId = comment.getCommentId();
            this.content = comment.getContent();
            this.secret = comment.isSecret();
            this.createdDate = comment.getCreatedDate();
            this.modifiedDate = comment.getModifiedDate();
            this.memberId = Long.valueOf(comment.getMember().getId());
            this.postsId = comment.getPosts().getPostId();
            this.nickname = comment.getMember().getNickname();
        }
    }
}
