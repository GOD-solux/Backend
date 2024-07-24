package com.godseven.muntour.openapi.service;

import java.util.List;

public interface OpenApiService<T> {
    String makeUrl(Integer pageNo);
    List<T> fetchData(Integer pageNo);
}
