package com.kali.blog.controller.file;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.kali.blog.common.aspect.Log;
import com.kali.blog.common.base.BaseController;
import com.kali.blog.common.response.Response;
import com.kali.blog.common.response.ResponseInfo;
import com.kali.blog.service.SysFileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统-文件上传 前端控制器
 *
 * @author Elliot
 * @since 2021-10-22
 */
@Log4j2
@RestController
@RequestMapping("file")
@Api(value = "文件信息", tags = "文件信息接口")
@ApiSupport(order = 5, author = "Elliot")
public class SysFileUploadController extends BaseController {

    @Resource
    private SysFileUploadService service;

    /**
     * 上传文件
     *
     * @param file     文件
     * @param filePath 要上传的文件路径
     * @return 上传结果
     */
    @Log("公共上传文件")
    @PostMapping(value = "uploadFile", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "上传文件", notes = "上传文件接口")
    @ApiOperationSupport(author = "Elliot", order = 0)
    public ResponseInfo<Response> uploadFile(
            @RequestPart(value = "file") MultipartFile file,
            @ApiParam(value = "文件上传路径", type = "String", required = true) String filePath) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return judgeResult(service.uploadFile(request, file, filePath));
    }
}
