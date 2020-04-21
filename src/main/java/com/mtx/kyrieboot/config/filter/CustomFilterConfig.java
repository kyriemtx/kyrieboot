package com.mtx.kyrieboot.config.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @ClassName CustomFilterConfig
 * @Description
 * @Author tengxiao.ma
 * @Date 2020/4/21 14:56
 **/
@Configuration
public class CustomFilterConfig {

    @Bean
    public Filter accessAuthFilter(){
        return new AccessAuthFilter();
    }
}
