package com.godseven.muntour.openapi.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SportsResponseList {
    private List<SportsResponse> sportsResponseList;

    public static SportsResponseList from(List<SportsResponse> sportsResponseList) {
        return new SportsResponseList(sportsResponseList);
    }
}
