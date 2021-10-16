package com.kali.blog.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.kali.blog.common.response.Response;
import com.kali.blog.common.response.ResponseEnum;
import com.kali.blog.common.response.ResponseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elliot
 */
@Log4j2
@RestController
@Api(value = "首页信息", tags = "首页信息接口")
@ApiSupport(order = 1, author = "Elliot")
public class IndexController {

    /**
     * buildName
     */
    @Value("${info.build.name}")
    private String buildName;
    /**
     * buildName
     */
    @Value("${info.build.artifactId}")
    private String artifactId;
    /**
     * 版本号
     */
    @Value("${info.build.version}")
    private String version;
    /**
     * 获取描述信息
     */
    @Value("${info.build.description}")
    private String description;

    /**
     * Index信息
     *
     * @return Index信息
     */
    @GetMapping("/")
    @ApiOperation(value = "获取首页信息", produces = "application/json")
    @ApiOperationSupport(author = "Elliot")
    public ResponseInfo<List<String>> index() {
        List<String> result = new ArrayList<>();
        result.add("Welcome To The " + buildName + " ~");
        result.add("Service Id : " + artifactId);
        result.add("Version : " + version);
        result.add("Project Description : " + description);
        result.add("Author : Elliot");
        log.info("result:{}", result);
        return Response.success(ResponseEnum.SUCCESS.getMessage(), result);
    }
}
