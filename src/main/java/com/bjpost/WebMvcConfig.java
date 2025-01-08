package com.bjpost;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String ALLOWED_METHOD_NAMES = "GET,HEAD,POST,DELETE,TRACE,OPTIONS,PATCH,PUT";

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**") // (1)Cors 정책을 모든 컨트롤러에 매핑
                .allowedOrigins("http://localhost:3000") // (2) React에서 사용되는 3000 포트 허용
                .allowedMethods(ALLOWED_METHOD_NAMES.split(",")); //(3) ALLOWED_METHOD_NAMES에 작성한 모든 HTTP Method를 허용
    }

}
