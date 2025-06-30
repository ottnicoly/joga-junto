package com.jogajunto.JogaJunto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // todos os endpoints
                        .allowedOrigins("http://localhost:3000") // libera seu frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // métodos permitidos
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}