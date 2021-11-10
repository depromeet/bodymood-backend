package com.depromeet.dgdg.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FilterConfig {

    @Profile({"dev", "prod"})
    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> requestLoggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> filter = new FilterRegistrationBean<>(new RequestLoggingFilter());
        filter.addUrlPatterns("/api/*");
        filter.setOrder(1);
        return filter;
    }

}
