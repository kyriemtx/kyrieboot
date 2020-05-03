package com.mtx.kyrieboot.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @ClassName MultipartConfig
 * @Description  文件上传配置信息
 * @Author tengxiao.ma
 * @Date 2020/5/3 1:51
 **/
@Configuration
public class MultipartConfig {

    /**
     * 对上传文件的配置
     * @return MultipartConfigElement配置实例
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置单个附件大小上限值(默认为1M)
        factory.setMaxFileSize(DataSize.parse("50MB"));
        // 设置所有附件的总大小上限值
        factory.setMaxRequestSize(DataSize.parse("250MB"));
        return factory.createMultipartConfig();
    }

}
