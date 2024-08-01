package com.godseven.muntour.post.dto;

import com.godseven.muntour.post.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private int id;
    private String title;
    private String content;
    private String writer;
    private String category;
    private String hashtag;

    public static PostDto toDto(Board board){
        return new PostDto(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getMember().getNickname(),
                board.getCategory(),
                null // 해시태그는 추후 로직에서 처리
        );
    }
}
