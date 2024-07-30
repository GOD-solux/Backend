package com.godseven.muntour.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequest {

    private String id;
    private String password;
    private String confirmPassword; // 회원가입 시에만 필요
    private String nickname; // 회원가입 시에만 필요
    private String muntourType; // 회원가입 시에만 필요
    private String imageUrl;   // 회원가입 시에만 필요
    private String imageFolder; // 회원가입 시에만 필요
    private String imageName;   // 회원가입 시에만 필요
}
