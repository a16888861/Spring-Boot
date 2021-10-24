package com.kali.blog.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Elliot
 */
@Log4j2
@Configuration
public class MyBatisPlusConfig {
    /**
     * 设置分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        DbType dbType = DbType.MYSQL;
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        /*数据源指定为MySQL*/
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(dbType));
        log.info("数据源指定为:" + dbType.getDesc());
        return interceptor;
    }
}