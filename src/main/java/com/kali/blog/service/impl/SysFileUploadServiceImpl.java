package com.kali.blog.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.kali.blog.common.aspect.LogAspect;
import com.kali.blog.common.base.BaseServiceImpl;
import com.kali.blog.common.constant.CommonConstants;
import com.kali.blog.common.context.UserContextUtil;
import com.kali.blog.common.util.FileUtil;
import com.kali.blog.dto.SysFileUploadDTO;
import com.kali.blog.entity.SysFileUpload;
import com.kali.blog.mapper.SysFileUploadMapper;
import com.kali.blog.service.SysFileUploadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;

/**
 * 系统-文件上传 服务实现类
 *
 * @author Elliot
 * @since 2021-10-22
 */
@Log4j2
@Service
public class SysFileUploadServiceImpl extends BaseServiceImpl<SysFileUploadMapper, SysFileUpload, SysFileUploadDTO> implements SysFileUploadService {

    /**
     * 上传文件
     *
     * @param request  请求
     * @param file     文件
     * @param filePath 文件位置
     * @return 上传结果
     */
    @Override
    public int uploadFile(HttpServletRequest request, MultipartFile file, String filePath) {

        File upload = FileUtil.upload(file, filePath);
        if (ObjectUtil.isNull(upload)) {
            return 0;
        }
        /*获取操作者的IP地址*/
        String clientIpAddress = LogAspect.getClientIpAddress(request);
        String originalFilename = file.getOriginalFilename();
        String uploadName = upload.getName();
        String suffix = FileUtil.getExtensionName(file.getOriginalFilename());
        log.info("开始记录上传位置:{上传原文件名为:{},文件重命名:{},文件扩展名:{},文件夹为:{},文件真实路径为:{} - 执行时间:{{}}}",
                originalFilename, uploadName, suffix, filePath + uploadName, upload, LocalDateTime.now().format(CommonConstants.DATE_TIME_FORMATTER));
        insert(SysFileUploadDTO.builder()
                .originalName(originalFilename).fileName(upload.getName()).fileSize(String.valueOf(file.getSize()))
                .fileSuffix(suffix).fileLocation(filePath).fileFullAddress(upload.getPath()).ip(clientIpAddress)
                .createBy(UserContextUtil.getUserInfo().getId()).build());
        log.info("文件上传结束～");
        return 1;
    }
}
