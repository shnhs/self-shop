package com.example.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    //CORS 세팅
    registry.addMapping("/**")
        .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS")
        .allowedOrigins("*");
  }
}
