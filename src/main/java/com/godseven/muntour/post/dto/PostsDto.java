package com.godseven.muntour.post.dto;

import com.godseven.muntour.member.domain.Member;
import com.godseven.muntour.post.entity.Posts;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

public class PostsDto {

    /** 게시글의 등록과 수정을 처리할 요청(Request) 클래스 */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long id;
        private String title;
        private String content;
        private String createdDate, modifiedDate;
        private Member member;

        /* Dto -> Entity */
        public Posts toEntity() {
            Posts posts = Posts.builder()
                    .postId(id)
                    .postTitle(title)
                    .content(content)
                    .build();

            return posts;
        }
    }

    /**
     * 게시글 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */
    @Getter
    public static class Response {
        private final Long id;
        private final String title;
        private final String content;
        private final String createdDate, modifiedDate;
        private final List<CommentDto.Response> comments;

        /* Entity -> Dto*/
        public Response(Posts posts) {
            this.id = posts.getPostId();
            this.title = posts.getPostTitle();
            this.content = posts.getContent();
            this.createdDate = posts.getCreatedDate();
            this.modifiedDate = posts.getModifiedDate();
            this.comments = posts.getComments().stream().map(CommentDto.Response::new).collect(Collectors.toList());
        }
    }
}
