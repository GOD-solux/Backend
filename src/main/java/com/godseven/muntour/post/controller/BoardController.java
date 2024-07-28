package com.godseven.muntour.post.controller;

import com.godseven.muntour.member.domain.Member;
import com.godseven.muntour.post.dto.BoardRequest;
import com.godseven.muntour.post.dto.BoardDto;
import com.godseven.muntour.post.repository.MemberRepository;
import com.godseven.muntour.post.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;
    private final MemberRepository memberRepository;

    // 전체 게시글 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/boards")
    public ResponseEntity<ApiResponse> getBoards() {
        return ResponseEntity.ok(new ApiResponse("성공", "전체 게시물 리턴", boardService.getBoards()));
    }

    // 게시글 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/boards/{id}")
    public ResponseEntity<ApiResponse> getBoard(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(new ApiResponse("성공", "개별 게시물 리턴", boardService.getBoard(id)));
    }

    // 게시글 작성
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/boards/write")
    //태그 관련> @RequestBody BoardDto boardDto>이걸 BoardRequest boardRequest로 변경
    public ResponseEntity<ApiResponse> write(@RequestBody BoardRequest boardRequest) {
        Member member = memberRepository.findById(1).orElseThrow(() -> new RuntimeException("Member not found"));
        //이것도 태그 추가하면서 추가
        BoardDto boardDto = boardRequest.toDto();
        //태그 관련> boardRequest.getTags() 추가
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("성공", "글 작성 성공", boardService.write(boardDto, member,boardRequest.getTags())));
    }

    // 게시글 수정
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/boards/update/{id}")
    //태그 관련> @RequestBody BoardDto boardDto>이걸 BoardRequest boardRequest로 변경
    public ResponseEntity<ApiResponse> edit(@RequestBody BoardRequest boardRequest, @PathVariable("id") Integer id) {
        Member member = memberRepository.findById(1).orElseThrow(() -> new RuntimeException("Member not found"));
        //이것도 태그 관련해서 추가
        BoardDto boardDto = boardRequest.toDto();
        //태그 관련> boardRequest.getTags() 추가
        return ResponseEntity.ok(new ApiResponse("성공", "글 수정 성공", boardService.update(id, boardDto,boardRequest.getTags())));
    }

    // 게시글 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/boards/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Integer id) {
        boardService.delete(id);
        return ResponseEntity.ok(new ApiResponse("성공", "글 삭제 성공", null));
    }

    // 게시글 검색 -> /boards/search?keyword=검색어 를 통해 찾을 수 있다.
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/boards/search")
    public ResponseEntity<ApiResponse> searchBoards(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(new ApiResponse("성공", "검색 결과 리턴", boardService.searchBoards(keyword)));
    }

    // 내부 클래스나 별도 파일로 정의할 수 있는 ApiResponse 클래스
    private static class ApiResponse {
        private String status;
        private String message;
        private Object data;

        public ApiResponse(String status, String message, Object data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        // Getter and Setter
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}

