package com.godseven.muntour.openapi.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class PerformanceConfig {
    @Value("${performance.service-key}")
    private String serviceKey;
}
