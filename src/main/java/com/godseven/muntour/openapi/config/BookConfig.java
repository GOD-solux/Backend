package com.godseven.muntour.openapi.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class BookConfig {
    @Value("${book.service-key}")
    private String serviceKey;
}
