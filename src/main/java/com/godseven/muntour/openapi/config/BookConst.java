package com.godseven.muntour.openapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookConst {
    public static final String ENDPOINT = "http://api.kcisa.kr/openapi/service/rest/meta13/getNLKF0201?";
    public static final String NUM_OF_ROWS = "&numOfRows=6";
    public static final String PAGE_NO = "&pageNo=";
    private static String serviceKey;

    @Autowired
    public BookConst(BookConfig bookConfig) {
        serviceKey = "serviceKey=" + bookConfig.getServiceKey();
    }

    public static String getServiceKey() {
        return serviceKey;
    }
}
