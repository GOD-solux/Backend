package com.godseven.muntour.openapi.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BookResponseList {
    private List<BookResponse> books;

    public static BookResponseList from(List<BookResponse> books) {
        return new BookResponseList(books);
    }
}