package com.godseven.muntour.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagMapping {
    @Id
    @GeneratedValue
    @Column(name = "TagMapping_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public static TagMapping create(Board board, Tag tag) {
        TagMapping tagMapping = new TagMapping();
        tagMapping.setBoard(board);
        tagMapping.setTag(tag);
        return tagMapping;
    }
}