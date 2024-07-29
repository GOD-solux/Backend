package com.godseven.muntour.openapi.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class OpenApiServiceUtil {

    private final RestTemplate restTemplate;
    private final JSONParser jsonParser;

    public <T> List<T> fetchData(String apiUrl, Function<JSONObject, T> mapper) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        List<T> result = new ArrayList<>();

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                JSONObject jsonResponse = (JSONObject) jsonParser.parse(response.getBody());
                JSONObject responseObj = (JSONObject) jsonResponse.get("response");
                JSONObject body = (JSONObject) responseObj.get("body");
                JSONObject items = (JSONObject) body.get("items");
                JSONArray itemArray = (JSONArray) items.get("item");

                for (Object obj : itemArray) {
                    JSONObject item = (JSONObject) obj;
                    result.add(mapper.apply(item));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
