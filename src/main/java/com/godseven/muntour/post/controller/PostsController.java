package com.godseven.muntour.post.controller;

import com.godseven.muntour.post.dto.CommentDto;
import com.godseven.muntour.post.dto.PostsDto;
import com.godseven.muntour.post.entity.Posts;
import com.godseven.muntour.post.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    /* CREATE */
    @PostMapping
    public ResponseEntity<Long> save(@RequestBody PostsDto.Request dto) {
        return ResponseEntity.ok(postsService.save(dto));
    }

    /* 게시글 목록 불러오기 */
    @GetMapping("/list")
    public String list(@RequestParam("category") String category, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Posts> list = postsService.findByCategory(category, pageable);

        model.addAttribute("posts", list);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());

        return "posts/posts-list";
    }

    /* 게시글 상세 보기 */
    @GetMapping("/{postNumber}")
    public ResponseEntity<PostsDto.Response> read(@PathVariable Long postNumber) {
        PostsDto.Response response = postsService.findById(postNumber);
        postsService.updateView(postNumber);
        return ResponseEntity.ok(response);
    }

    /* 게시글 수정*/
    @PutMapping("/{postNumber}")
    public ResponseEntity<Long> update(@PathVariable Long postNumber, @RequestBody PostsDto.Request dto) {
        postsService.update(postNumber, dto);
        return ResponseEntity.ok(postNumber);
    }

    /* 게시글 삭제  */
    @DeleteMapping("/{postNumber}")
    public ResponseEntity<Long> delete(@PathVariable Long postNumber) {
        postsService.delete(postNumber);
        return ResponseEntity.ok(postNumber);
    }

    /* 게시글 검색 */
    @GetMapping("/search-list/{searchWord}")
    public ResponseEntity<Page<Posts>> search(@PathVariable String searchWord, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(postsService.search(searchWord, pageable));
    }
}
