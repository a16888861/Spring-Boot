package com.kali.blog.controller.elasticSearch;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ES相关控制层面
 *
 * @author Elliot
 * @date 2021/11/21 6:41 下午
 */
@Log4j2
@RestController
@RequestMapping("es")
@Api(value = "ES相关", tags = "ES信息接口")
@ApiSupport(order = 1, author = "Elliot")
public class ElasticSearchController {
}
