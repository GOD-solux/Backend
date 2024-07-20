package com.godseven.muntour.openapi.service;

import com.godseven.muntour.openapi.config.ExhibitionConst;
import com.godseven.muntour.openapi.dto.response.ExhibitionResponse;
import com.godseven.muntour.openapi.dto.response.ExhibitionResponseList;
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
public class ExhibitionService {

    /**
     * 전시회 리스트 불러오기
     */
    public ExhibitionResponseList getExhibitionList(int pageNo) {
        String apiUrl = makeExhibitionUrl(pageNo);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        List<ExhibitionResponse> exhibitions = new ArrayList<>();

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
                    exhibitions.add(
                            ExhibitionResponse.builder()
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
        return ExhibitionResponseList.from(exhibitions);
    }

    private String makeExhibitionUrl(int pageNo) {
        String baseUrl = ExhibitionConst.ENDPOINT
                + ExhibitionConst.getServiceKey()
                + ExhibitionConst.NUM_OF_ROWS
                + ExhibitionConst.PAGE_NO
                + pageNo;
        return baseUrl;
    }

}
