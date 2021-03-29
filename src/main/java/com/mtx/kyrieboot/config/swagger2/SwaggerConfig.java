package com.mtx.kyrieboot.config.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Auther: kyriemtx
 * @Date: 2021/3/29 10:58
 * @Description:
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket CreateRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mtx.kyrieboot.controller.real"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("德鲁大叔管理系统接口文档")
                .description("restFul api 文档构建利器")
                .termsOfServiceUrl("https://blog.csdn.net/m0_43584016")
                .contact("社会主义打工人")
                .version("0.0.1")
                .build();
    }

}
