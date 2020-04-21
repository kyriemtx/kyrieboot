package com.mtx.kyrieboot.config.security;

import com.mtx.kyrieboot.config.interceptor.AccessAuthHandlerInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName SecurityMvcConfigurer
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 16:26
 **/
@AllArgsConstructor
@Configuration
public class SecurityMvcConfigurer implements WebMvcConfigurer {

    //url访问权限验证拦截器
    private AccessAuthHandlerInterceptor accessAuthHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册过滤器
        registry.addInterceptor(accessAuthHandlerInterceptor).excludePathPatterns("/static/**").addPathPatterns("/**");
    }


    /**
     * 页面跳转
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login.html");
        registry.addViewController("/403").setViewName("403.html");
    }

}
