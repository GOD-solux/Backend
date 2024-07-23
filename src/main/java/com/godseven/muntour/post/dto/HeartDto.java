package com.godseven.muntour.post.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartDto {
    private Long memberId;
    private Long postsId;

    public HeartDto(Long memberId, Long postsId){
        this.memberId=memberId;
        this.postsId=postsId;
    }
}
