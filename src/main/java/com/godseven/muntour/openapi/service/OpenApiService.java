package com.godseven.muntour.openapi.service;

import java.util.List;

public interface OpenApiService<T> {
    String makeUrl(int pageNo);
    List<T> fetchData(int pageNo);
}
