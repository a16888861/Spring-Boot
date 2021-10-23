package com.kali.blog.controller.file;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.kali.blog.common.aspect.Log;
import com.kali.blog.common.base.BaseController;
import com.kali.blog.common.base.CommonPage;
import com.kali.blog.common.response.Response;
import com.kali.blog.common.response.ResponseEnum;
import com.kali.blog.common.response.ResponseInfo;
import com.kali.blog.service.SysFileUploadService;
import com.kali.blog.vo.SysFileUploadVO;
import com.kali.blog.vo.SysFileUploadVOPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

    /**
     * 下载文件
     *
     * @param id 文件id
     * @return 结果
     */
    //TODO 下载文件接口暂未完成
    @Log("公共下载文件")
    @PostMapping(value = "downloadFile")
    @ApiOperation(value = "下载文件", notes = "下载文件接口")
    @ApiOperationSupport(author = "Elliot", order = 1)
    public ResponseInfo<Response> downloadFile(@ApiParam(value = "文件ID", type = "String", required = true) String id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return judgeResult(1);
    }

    /**
     * 查询文件列表分页信息
     *
     * @param filePage      查询条件
     * @param bindingResult 判断参数
     * @return 查询结果
     */
    @Log("查询文件列表分页信息")
    @PostMapping(value = "selectFileList")
    @ApiOperation(value = "查询文件列表分页信息", notes = "查询文件列表分页信息接口")
    @ApiOperationSupport(author = "Elliot", order = 2)
    public ResponseInfo<CommonPage<SysFileUploadVO>> selectFileList(@Valid @RequestBody SysFileUploadVOPage filePage, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        CommonPage<SysFileUploadVO> selectFilePage = service.selectFilePage(filePage);
        if (selectFilePage == null) {
            return Response.notFound("common.response.notfound");
        }
        return Response.success(ResponseEnum.SUCCESS.getMessage(), selectFilePage);
    }
}
