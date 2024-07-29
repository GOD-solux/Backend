package com.godseven.muntour.openapi.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ExhibitionResponseList {
    private List<ExhibitionResponse> exhibitionResponseList;

    public static ExhibitionResponseList from (List<ExhibitionResponse> exhibitionResponses) {
        return new ExhibitionResponseList(exhibitionResponses);
    }
}