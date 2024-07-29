package com.godseven.muntour.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinRequestDto {

    private Long memberId;
    private String nickname;
    private String password;

}
