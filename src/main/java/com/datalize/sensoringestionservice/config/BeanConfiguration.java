package com.datalize.sensoringestionservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class BeanConfiguration {
    @Bean
    public ObjectMapper getObjectMapper() {
            return new ObjectMapper();
    }
}
