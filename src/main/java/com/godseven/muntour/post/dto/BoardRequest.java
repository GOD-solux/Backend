package com.godseven.muntour.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {
    private String title;
    private String content;
    private String writer;
    private List<String> tags;

    public BoardDto toDto() {
        return new BoardDto(0, title, content, writer);
    }
}