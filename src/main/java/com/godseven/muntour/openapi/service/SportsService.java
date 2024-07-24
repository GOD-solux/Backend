package com.godseven.muntour.openapi.service;

import com.godseven.muntour.openapi.config.PerformanceConst;
import com.godseven.muntour.openapi.config.SportsConst;
import com.godseven.muntour.openapi.dto.response.PerformanceResponse;
import com.godseven.muntour.openapi.dto.response.PerformanceResponseList;
import com.godseven.muntour.openapi.dto.response.SportsResponse;
import com.godseven.muntour.openapi.dto.response.SportsResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class SportsService implements OpenApiService<SportsResponse>{

    private final OpenApiServiceUtil openApiServiceUtil;

    /**
     * 공연 목록 불러오기
     */
    public SportsResponseList getSportsList(Integer pageNo) {
        List<SportsResponse> sportsResponseList = fetchData(pageNo);
        return SportsResponseList.from(sportsResponseList);
    }

    @Override
    public String makeUrl(Integer pageNo) {
        String url = SportsConst.ENDPOINT
                + SportsConst.getServiceKey()
                + SportsConst.NUM_OF_ROWS
                + SportsConst.PAGE_NO
                + pageNo;
        return url;
    }

    @Override
    public List<SportsResponse> fetchData(Integer pageNo) {
        String apiUrl = makeUrl(pageNo);
        return openApiServiceUtil.fetchData(apiUrl, item ->
                SportsResponse.builder()
                        .title((String) item.get("title"))
                        .publisher((String) item.get("publisher"))
                        .venue((String) item.get("venue"))
                        .url(null)
                        .build()
        );
    }
}
