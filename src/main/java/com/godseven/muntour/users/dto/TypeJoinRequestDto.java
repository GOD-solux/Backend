package com.godseven.muntour.users.dto;

public class TypeJoinRequestDto {

    private Long memberId;

    // 기본 생성자
    public TypeJoinRequestDto() {
    }

    // 모든 필드를 초기화하는 생성자
    public TypeJoinRequestDto(Long memberId) {
        this.memberId = memberId;
    }

    // Getter와 Setter 메소드
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

}
