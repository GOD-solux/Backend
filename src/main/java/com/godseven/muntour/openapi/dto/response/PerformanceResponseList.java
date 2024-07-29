package com.godseven.muntour.openapi.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PerformanceResponseList {
    List<PerformanceResponse> performanceResponseList;

    public static PerformanceResponseList from (List<PerformanceResponse> performanceResponses) {
        return new PerformanceResponseList(performanceResponses);
    }
}
