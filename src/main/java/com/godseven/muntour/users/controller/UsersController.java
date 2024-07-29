package com.godseven.muntour.users.controller;

import com.godseven.muntour.member.domain.Member;
import com.godseven.muntour.users.dto.ImageRequestDto;
import com.godseven.muntour.users.dto.PasswordRequestDto;
import com.godseven.muntour.users.dto.TypeJoinResponseDto;
import com.godseven.muntour.users.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UsersController", description = "마이페이지")
@Slf4j
@RequiredArgsConstructor
@RestController
public class UsersController {

    // domain은 Member 클래스 사용
    // dto는 controller 기능의 5개 생성
    // repository는 모르겠음
    // service 1개 생성

    private final UsersService usersService;

    // 테스트 유형 결과 불러오기 GET
    @Operation(summary = "테스트 유형 결과 불러오기", description = "테스트 유형 결과 불러오기")
    @GetMapping("/users/muntour-type")
    public TypeJoinResponseDto getMuntourType(@Valid @RequestParam("member_id") Long memberId) {
        return usersService.getMuntourType(memberId);
    }

    // 유저 정보 불러오기 -> membr에서 가져오면 될 듯 GET
    @Operation(summary = "유저 정보 불러오기", description = "유저 정보 불러오기")
    @GetMapping("/users")
    public ResponseEntity<Member> findByMemberId(@Valid @RequestParam("member_id") Long memberId) {
        try {
            Member memberProfile = usersService.findByMemberId(memberId);
            return ResponseEntity.ok(memberProfile);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 비밀번호 변경하기 PATCH
    @Operation(summary = "비밀번호 변경하기", description = "비밀번호 변경하기")
    @PatchMapping("/users/change-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordRequestDto passwordRequestDto) {
        boolean isReset = usersService.resetPassword(passwordRequestDto.getMemberId(), passwordRequestDto.getNewPassword());
        if (isReset) {
            return ResponseEntity.ok("비밀번호가 재설정되었습니다.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //    // 프로필 사진 변경하기 PATCH
    @Operation(summary = "프로필 사진 변경하기", description = "프로필 사진 변경하기")
    @PatchMapping("/profile-img")
    public Long update(@RequestBody ImageRequestDto requestDto) {
        return usersService.update(requestDto);
    }

//    // 닉네임 변경하기 PATCH
//    @PatchMapping("/users/nickname")
//    // @PutMapping("/category/editfoldername/{folderUuid}")
//    public Long update(@PathVariable Long folderUuid, @RequestBody FoldersUpdateRequestDto requestDto){
//        return foldersService.update(folderUuid, requestDto);
//    }
}
