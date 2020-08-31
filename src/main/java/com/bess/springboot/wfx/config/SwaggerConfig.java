package com.bess.springboot.wfx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/29 9:46
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket getDocket(){
        //Docket对象用于设置接口说明信息
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())   //设置说明文档的“封面”信息
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bess.springboot.wfx.controller"))  //指定扫描接口的范围
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    public ApiInfo getApiInfo(){
        ApiInfoBuilder builder = new ApiInfoBuilder()
                .title("微分销管理平台接口说明文档")          //设置标题
                .description("这是api接口说明文档的描述信息")
                .version("1.0.0")
                .contact(new Contact("BessCroft","https://52bess.com","besscroft@foxmail.com"));

        ApiInfo apiInfo = builder.build();
        return apiInfo;
    }
}
