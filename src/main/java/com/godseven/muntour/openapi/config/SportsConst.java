package com.godseven.muntour.openapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SportsConst {
    public static final String ENDPOINT = "http://api.kcisa.kr/openapi/API_SPO_044/request?";
    public static final String NUM_OF_ROWS = "&numOfRows=6";
    public static final String PAGE_NO = "&pageNo=";
    private static String serviceKey;

    @Autowired
    public SportsConst(SportsConfig sportsConfig) {
        serviceKey = "serviceKey=" + sportsConfig.getServiceKey();
    }

    public static String getServiceKey() {
        return serviceKey;
    }
}
