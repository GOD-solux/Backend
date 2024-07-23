package com.godseven.muntour.post.entity;

import com.godseven.muntour.member.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

//    @Enumerated(EnumType.STRING)
//    private PostCategory postCategory;

    @Column(length = 500, nullable = false)
    private String postTitle;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private String imageUrl;

    @Column
    private String imageFolder;

    @Column
    private String imageName;

//    @Column(name = "hashtag")
//    private String hashtag;

//    @Column(columnDefinition = "integer default 0", nullable = false)
//    private int view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<Comment> comments;

    /* 게시글 수정 */
    public void update(String postTitle, String content) {
        this.postTitle = postTitle;
        this.content = content;
    }
}