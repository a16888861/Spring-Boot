package com.kali.blog;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() {
        log.info("测试方法输出:contextLoads===>{}", "SpringBootTest");
    }
}
