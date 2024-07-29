package com.godseven.muntour.global.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Collections;

@Getter
@Builder
public class ResponseTemplate<T> {
    private final String code;
    private final String message;
    private final T results;

    public static <T> ResponseTemplate<T> from(T dto) {
        return ResponseTemplate.<T>builder()
                .code("SUCCESS")
                .message("Request processed successfully")
                .results(dto)
                .build();
    }

    public static final ResponseTemplate<Object> EMPTY_RESPONSE = ResponseTemplate.builder()
                .code("SUCCESS")
                .message("Request processed successfully")
                .results(Collections.emptyMap())
                .build();
}