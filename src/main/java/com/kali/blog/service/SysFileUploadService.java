package com.kali.blog.service;

import com.kali.blog.common.base.BaseService;
import com.kali.blog.common.base.CommonPage;
import com.kali.blog.dto.SysFileUploadDTO;
import com.kali.blog.entity.SysFileUpload;
import com.kali.blog.vo.SysFileUploadVO;
import com.kali.blog.vo.SysFileUploadVOPage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统-文件上传 服务类
 *
 * @author Elliot
 * @since 2021-10-22
 */
public interface SysFileUploadService extends BaseService<SysFileUpload, SysFileUploadDTO> {

    /**
     * 上传文件
     *
     * @param request  请求
     * @param file     文件
     * @param filePath 文件位置
     * @return 上传结果
     */
    int uploadFile(HttpServletRequest request, MultipartFile file, String filePath);

    /**
     * 查询文件列表分页信息
     *
     * @param filePage 查询条件
     * @return 查询结果
     */
    CommonPage<SysFileUploadVO> selectFilePage(SysFileUploadVOPage filePage);
}
