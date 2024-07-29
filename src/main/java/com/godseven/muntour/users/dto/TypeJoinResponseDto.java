package com.godseven.muntour.users.dto;

import com.godseven.muntour.member.domain.type.Muntour;

public class TypeJoinResponseDto {

    private Muntour muntourType;

    public TypeJoinResponseDto(){

    }

    public TypeJoinResponseDto(Muntour muntourType) {
        this.muntourType = muntourType;
    }

    public Muntour getMuntourType() {
        return muntourType;
    }

    public void setMuntourType(Muntour muntourType) {
        this.muntourType = muntourType;
    }
}
