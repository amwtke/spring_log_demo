package com.example.mdc.web;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<TraceMdcFilter> traceMdcFilter() {
        FilterRegistrationBean<TraceMdcFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new TraceMdcFilter());
        bean.setOrder(1);
        bean.addUrlPatterns("/*");
        return bean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserMdcInterceptor())
                .addPathPatterns("/**");
    }
}
