package com.learn.web.config;

import com.learn.web.handler.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //生成拦截器的使用，传入LoginInterceptor对象
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        //拦截所有的请求
        registration.addPathPatterns("/**");
        //定义不拦截的请求
        registration.excludePathPatterns("/toHandler","/toLogin","/toLogin1","/error");
    }
}
