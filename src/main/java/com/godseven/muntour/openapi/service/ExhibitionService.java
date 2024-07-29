package com.godseven.muntour.openapi.service;

import com.godseven.muntour.openapi.config.ExhibitionConst;
import com.godseven.muntour.openapi.dto.response.ExhibitionResponse;
import com.godseven.muntour.openapi.dto.response.ExhibitionResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ExhibitionService implements OpenApiService<ExhibitionResponse> {

    private final OpenApiServiceUtil openApiServiceUtil;

    /**
     * 전시회 리스트 불러오기
     */
    public ExhibitionResponseList getExhibitionList(Integer pageNo) {
        List<ExhibitionResponse> exhibitions = fetchData(pageNo);
        return ExhibitionResponseList.from(exhibitions);
    }

    @Override
    public String makeUrl(Integer pageNo) {
        String url = ExhibitionConst.ENDPOINT
                + ExhibitionConst.getServiceKey()
                + ExhibitionConst.NUM_OF_ROWS
                + ExhibitionConst.PAGE_NO
                + pageNo;
        return url;
    }

    @Override
    public List<ExhibitionResponse> fetchData(Integer pageNo) {
        String apiUrl = makeUrl(pageNo);
        return openApiServiceUtil.fetchData(apiUrl, item ->
                ExhibitionResponse.builder()
                        .title((String) item.get("TITLE"))
                        .imageObject((String) item.get("IMAGE_OBJECT"))
                        .url((String) item.get("URL"))
                        .period((String) item.get("PERIOD"))
                        .build()
        );
    }
}