package com.godseven.muntour.openapi.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class SportsConfig {
    @Value("${sports.service-key}")
    private String serviceKey;
}
