package com.godseven.muntour.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequest {

    private String id;
    private String password;
    private String nickname; // 회원가입 시에만 필요
    private String muntourType; // 회원가입 시에만 필요
    private String imageUrl;   // 회원가입 시에만 필요
    private String imageFolder; // 회원가입 시에만 필요
    private String imageName;   // 회원가입 시에만 필요

    // 로그인 시에는 nickname, muntourType, imageUrl, imageFolder, imageName 필드는 null일 수 있습니다.
}
