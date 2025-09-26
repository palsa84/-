// WebConfig.java (최소 수정)

package com.example.demo.WebConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath = "/upload/**";
    // 경로 자체는 원본 그대로 유지합니다. (file:///C:/project_img/)
    private String savePath = "file:///C:/project_img/";

    // 🚨 1. 메서드 이름을 addResourceHandlers (복수형)로 변경합니다.
    // 🚨 2. @Override 어노테이션을 추가하여 오버라이딩을 명확히 합니다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }
}