package com.godseven.muntour.member.domain;

import com.godseven.muntour.member.domain.type.Muntour;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    @Getter
    @Setter
    private Long memberId;

    @Column(name = "id", length = 30)
    private String id;

    @Getter
    @Setter
    @Column(name = "password", length = 30)
    private String password;

    @Column(name = "nickname", length = 30)
    private String nickname;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "muntour_type")
    private Muntour muntourType;

    @Getter
    @Setter
    @Column(name = "image_url")
    private String imageUrl;

    @Getter
    @Setter
    @Column(name = "image_folder", length = 50)
    private String imageFolder;

    @Getter
    @Setter
    @Column(name = "image_name", length = 100)
    private String imageName;

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Interest> interestList = new ArrayList<>();

    @Builder
    public Member(String id, String password, String nickname, Muntour muntourType, List<Interest> interestList, String imageUrl, String imageFolder, String imageName) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.muntourType = muntourType;
        this.interestList = interestList;
        this.imageUrl = imageUrl;
        this.imageFolder = imageFolder;
        this.imageName = imageName;
    }
}
