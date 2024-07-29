package com.godseven.muntour.openapi.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SportsResponse {
    private String title;
    private String publisher;
    private String url;
    private String venue;
}
