package com.godseven.muntour.openapi.service;

import com.godseven.muntour.openapi.config.PerformanceConst;
import com.godseven.muntour.openapi.dto.response.PerformanceResponse;
import com.godseven.muntour.openapi.dto.response.PerformanceResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PerformanceService implements OpenApiService<PerformanceResponse>{

    private final OpenApiServiceUtil openApiServiceUtil;

    /**
     * 공연 목록 불러오기
     */
    public PerformanceResponseList getPerformanceList(Integer pageNo) {
        List<PerformanceResponse> performanceResponses = fetchData(pageNo);
        return PerformanceResponseList.from(performanceResponses);
    }

    @Override
    public String makeUrl(Integer pageNo) {
        String url = PerformanceConst.ENDPOINT
                + PerformanceConst.getServiceKey()
                + PerformanceConst.NUM_OF_ROWS
                + PerformanceConst.PAGE_NO
                + pageNo;
        return url;
    }

    @Override
    public List<PerformanceResponse> fetchData(Integer pageNo) {
        String apiUrl = makeUrl(pageNo);
        return openApiServiceUtil.fetchData(apiUrl, item ->
                PerformanceResponse.builder()
                        .title((String) item.get("TITLE"))
                        .imageObject((String) item.get("IMAGE_OBJECT"))
                        .url((String) item.get("URL"))
                        .period((String) item.get("PERIOD"))
                        .build()
        );
    }
}