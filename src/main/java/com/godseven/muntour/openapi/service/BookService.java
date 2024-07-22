package com.godseven.muntour.openapi.service;

import com.godseven.muntour.openapi.config.BookConst;
import com.godseven.muntour.openapi.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BookService implements OpenApiService<BookResponse> {

    private final OpenApiServiceUtil openApiServiceUtil;

    /**
     * 추천 도서 목록 보기
     */
    public BookResponseList getBookList(int pageNo) {
        List<BookResponse> bookResponses = fetchData(pageNo);
        return BookResponseList.from(bookResponses);
    }

    @Override
    public String makeUrl(int pageNo) {
        String url = BookConst.ENDPOINT
                + BookConst.getServiceKey()
                + BookConst.NUM_OF_ROWS
                + BookConst.PAGE_NO
                + pageNo;
        return url;
    }

    @Override
    public List<BookResponse> fetchData(int pageNo) {
        String apiUrl = makeUrl(pageNo);
        return openApiServiceUtil.fetchData(apiUrl, item ->
                BookResponse.builder()
                        .title((String) item.get("title"))
                        .imageObject((String) item.get("referenceIdentifier"))
                        .subjectCategory((String) item.get("subjectCategory"))
                        .author((String) item.get("rights"))
                        .build()
        );
    }
}