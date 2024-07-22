package com.godseven.muntour.openapi.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookResponse {
    private String title;
    private String imageObject;
    private String subjectCategory;
    private String author;
}
