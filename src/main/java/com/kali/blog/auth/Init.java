package com.kali.blog.auth;

import com.kali.blog.common.util.JwtUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Elliot
 * @date 2021/10/15 12:42
 */
@Log4j2
@Component
public class Init {
    /**
     * 启动时自动加载
     */
    @PostConstruct
    public void init(){
        log.info("JwtAuth:加载Jwt初始化数据认证~");
        JwtUtil.init();
    }
}
