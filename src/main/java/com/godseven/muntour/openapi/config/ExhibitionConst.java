package com.godseven.muntour.openapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExhibitionConst {
    public static final String ENDPOINT = "http://api.kcisa.kr/openapi/API_CCA_145/request?";
    public static final String NUM_OF_ROWS = "&numOfRows=6";
    public static final String PAGE_NO = "&pageNo=";
    private static String serviceKey;

    @Autowired
    public ExhibitionConst(ExhibitionConfig exhibitionConfig) {
        serviceKey = "serviceKey=" + exhibitionConfig.getServiceKey();
    }

    public static String getServiceKey() {
        return serviceKey;
    }
}
