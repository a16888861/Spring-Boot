package com.kali.springboot;

import com.kali.springboot.common.context.SpringContextHolder;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author Elliot
 */
@Log4j2
@PropertySource("/application.properties")
@SpringBootApplication
@ComponentScans(value = {
        @ComponentScan("com.kali.springboot.controller"),
        @ComponentScan("com.kali.springboot.service"),
        @ComponentScan("com.kali.springboot.mapper"),
        @ComponentScan("com.kali.springboot.config"),
})
public class BlogApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BlogApplication.class, args);
        Environment environment = context.getBean(Environment.class);
        log.info("Blog Start Success ~");
        log.info("The Api Doc Address Is:\thttp://localhost:" + environment.getProperty("server.port") + "/doc.html");
    }

    /**
     * 注入上下文工具类
     */
    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    /**
     * tomcat拦截特殊字符处理
     */
    @Bean
    public ServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory fa = new TomcatServletWebServerFactory();
        fa.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "[]{}"));
        return fa;
    }
}
