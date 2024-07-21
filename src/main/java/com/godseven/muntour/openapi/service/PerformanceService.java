package com.godseven.muntour.openapi.service;

import com.godseven.muntour.openapi.config.PerformanceConst;
import com.godseven.muntour.openapi.dto.response.PerformanceResponse;
import com.godseven.muntour.openapi.dto.response.PerformanceResponseList;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
public class PerformanceService {

    private String makePerformanceUrl(int pageNo) {
        String baseUrl = PerformanceConst.ENDPOINT
                + PerformanceConst.getServiceKey()
                + PerformanceConst.NUM_OF_ROWS
                + PerformanceConst.PAGE_NO;
        String pageNoStr = String.valueOf(pageNo);
        return baseUrl + pageNoStr;
    }

    public PerformanceResponseList getPerformanceList(int pageNo) {
        String apiUrl = makePerformanceUrl(pageNo);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        List<PerformanceResponse> performanceResponses = new ArrayList<>();

        if (response.getStatusCode() == HttpStatus.OK) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject jsonResponse = (JSONObject) parser.parse(response.getBody());
                JSONObject responseObj = (JSONObject) jsonResponse.get("response");
                JSONObject body = (JSONObject) responseObj.get("body");
                JSONObject items = (JSONObject) body.get("items");
                JSONArray itemArray = (JSONArray) items.get("item");

                for (Object obj : itemArray) {
                    JSONObject item = (JSONObject) obj;
                    String title = (String) item.get("TITLE");
                    String imageObject = (String) item.get("IMAGE_OBJECT");
                    String url = (String) item.get("URL");
                    String period = (String) item.get("PERIOD");
                    performanceResponses.add(
                            PerformanceResponse.builder()
                                    .title(title)
                                    .imageObject(imageObject)
                                    .url(url)
                                    .period(period).build()
                            );
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return PerformanceResponseList.from(performanceResponses);
    }

}
