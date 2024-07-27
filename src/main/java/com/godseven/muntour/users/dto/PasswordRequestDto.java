package com.godseven.muntour.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordRequestDto {

    private Long memberId;
    private String newPassword;
}
