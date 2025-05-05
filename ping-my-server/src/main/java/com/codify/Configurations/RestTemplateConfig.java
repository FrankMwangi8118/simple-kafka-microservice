package com.codify.Configurations;

import org.springframework.web.client.RestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

public class RestTemplateConfig {
    @Bean
    public org.springframework.web.client.RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
