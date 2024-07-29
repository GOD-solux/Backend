package com.godseven.muntour.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag{

    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    @Column(name = "word", nullable = false)
    private String word;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<TagMapping> tagMapping;


    public static Tag create(String word) {
        Tag tag = new Tag();
        tag.setWord(word);
        return tag;
    }
}