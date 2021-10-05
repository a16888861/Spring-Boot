package com.kali.springboot.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger配置类
 *
 * @author Elliot
 */
@Configuration
public class Swagger2Config {

    @Value("${knife4j.enable}")
    public boolean knife4jEnable;

    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public Swagger2Config(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean(value = "博客接口文档")
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(knife4jEnable)
                .groupName("博客接口文档")
                .select()
                /*采用包扫描的方式来确定要显示的接口*/
                .apis(RequestHandlerSelectors.basePackage("com.kali.springboot.controller"))
                /*采用包含注解的方式来确定要显示的接口(两种方式：根据类注释和根据方法注释，看情况选择)*/
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                /*扫描全部*/
                .paths(PathSelectors.any())
                /*扫描指定*/
//                .paths(PathSelectors.regex("/index/*"))
                .build()
                .extensions(openApiExtensionResolver.buildExtensions("博客接口文档"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Blog RESTFUL APIs(Swagger-3.0.3)")
                .description("博客接口文档")
                .contact(new Contact("Elliot", "https://xstrojan.top/about/", "a12888821@gmail.com"))
                .version("1.0")
                .termsOfServiceUrl("https://github.com/a16888861/Spring-Boot")
                .build();
    }
}