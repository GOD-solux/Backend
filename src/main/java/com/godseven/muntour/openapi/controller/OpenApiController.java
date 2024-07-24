package com.godseven.muntour.openapi.controller;

import com.godseven.muntour.openapi.dto.response.BookResponseList;
import com.godseven.muntour.openapi.dto.response.ExhibitionResponseList;
import com.godseven.muntour.openapi.dto.response.PerformanceResponseList;
import com.godseven.muntour.openapi.dto.response.SportsResponseList;
import com.godseven.muntour.openapi.service.BookService;
import com.godseven.muntour.openapi.service.ExhibitionService;
import com.godseven.muntour.global.dto.ResponseTemplate;
import com.godseven.muntour.openapi.service.PerformanceService;
import com.godseven.muntour.openapi.service.SportsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "OpenApiController", description = "문화 생활 더보기 API (전시, 공연, 도서, 스포츠)")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/muntour/open-api")
public class OpenApiController {

    private final ExhibitionService exhibitionService;
    private final PerformanceService performanceService;
    private final BookService bookService;
    private final SportsService sportsService;

    @Operation(summary = "전시회 목록보기", description = "전시회 목록보기")
    @GetMapping("/exhibition")
    public ResponseEntity<ResponseTemplate<Object>> getExhibitionList(@RequestParam(defaultValue = "1") Integer pageNo) {

        ExhibitionResponseList responses = exhibitionService.getExhibitionList(pageNo);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(responses));
    }

    @Operation(summary = "공연 목록보기", description = "공연 목록보기")
    @GetMapping("/performance")
    public ResponseEntity<ResponseTemplate<Object>> getPerformanceList(@RequestParam(defaultValue = "1") Integer pageNo) {

        PerformanceResponseList responses = performanceService.getPerformanceList(pageNo);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(responses));
    }

    @Operation(summary = "추천 도서 목록보기", description = "추천 도서 목록보기")
    @GetMapping("/book")
    public ResponseEntity<ResponseTemplate<Object>> getBookList(@RequestParam(defaultValue = "1") Integer pageNo) {

        BookResponseList responses = bookService.getBookList(pageNo);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(responses));
    }

    @Operation(summary = "추천 도서 목록보기", description = "추천 도서 목록보기")
    @GetMapping("/sports")
    public ResponseEntity<ResponseTemplate<Object>> getSportsList(@RequestParam(defaultValue = "1") Integer pageNo) {

        SportsResponseList responses = sportsService.getSportsList(pageNo);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(responses));
    }
}