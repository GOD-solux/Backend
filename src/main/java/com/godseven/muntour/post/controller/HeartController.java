package com.godseven.muntour.post.controller;

import com.godseven.muntour.post.dto.HeartDto;
import com.godseven.muntour.post.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/heart")
public class HeartController {

    private final HeartService heartService;

    @PostMapping
    public ResponseEntity<Void> addHeart(@RequestBody HeartDto heartDto) {
        heartService.addHeart(heartDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeHeart(@RequestBody HeartDto heartDto) {
        heartService.removeHeart(heartDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/count/{postsId}")
    public ResponseEntity<Long> countHearts(@PathVariable Long postsId) {
        long heartCount = heartService.countHearts(postsId);
        return ResponseEntity.ok(heartCount);
    }
}
