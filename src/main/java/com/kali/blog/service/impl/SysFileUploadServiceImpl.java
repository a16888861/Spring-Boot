package com.kali.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kali.blog.common.aspect.LogAspect;
import com.kali.blog.common.base.BaseEntity;
import com.kali.blog.common.base.BaseServiceImpl;
import com.kali.blog.common.base.CommonPage;
import com.kali.blog.common.constant.CommonConstants;
import com.kali.blog.common.context.UserContextUtil;
import com.kali.blog.common.util.FileUtil;
import com.kali.blog.common.util.PageUtil;
import com.kali.blog.dto.SysFileUploadDTO;
import com.kali.blog.entity.SysFileUpload;
import com.kali.blog.mapper.SysFileUploadMapper;
import com.kali.blog.service.SysFileUploadService;
import com.kali.blog.vo.SysFileUploadVO;
import com.kali.blog.vo.SysFileUploadVOPage;
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
        String userId = UserContextUtil.getUserInfo().getId();
        String suffix = FileUtil.getExtensionName(file.getOriginalFilename());
        String fileType = FileUtil.getFileType(suffix);
        /*不同的用户对应不同的文件夹-对应日期做区分*/
        File upload = FileUtil.upload(file, filePath + userId + "/" + fileType + "/" + DateUtil.now() + "/");
        if (ObjectUtil.isNull(upload)) {
            return 0;
        }
        /*获取操作者的IP地址*/
        String clientIpAddress = LogAspect.getClientIpAddress(request);
        String originalFilename = file.getOriginalFilename();
        String uploadName = upload.getName();
        log.info("开始记录上传位置:{上传原文件名为:{},文件重命名:{},文件扩展名:{},文件类型:{},文件夹为:{},文件真实路径为:{} - 执行时间:{{}}}",
                originalFilename, uploadName, suffix, fileType, filePath + uploadName, upload, LocalDateTime.now().format(CommonConstants.DATE_TIME_FORMATTER));
        insert(SysFileUploadDTO.builder()
                .originalName(originalFilename).fileName(upload.getName()).fileSize(String.valueOf(file.getSize()))
                .fileSuffix(suffix).fileType(fileType).fileLocation(filePath).fileFullAddress(upload.getPath())
                .ip(clientIpAddress).createBy(userId).build());
        return 1;
    }

    /**
     * 查询文件列表分页信息
     *
     * @param filePage 查询条件
     * @return 查询结果
     */
    @Override
    public CommonPage<SysFileUploadVO> selectFilePage(SysFileUploadVOPage filePage) {
        Page<SysFileUploadDTO> page = new Page<>();
        page.setSize(filePage.getPageSize()).setCurrent(filePage.getPageCurrent());
        //TODO 查询条件待完善
        Page<SysFileUploadDTO> dtoPage = selectPage(page, new LambdaQueryWrapper<SysFileUpload>()
                .eq(BaseEntity::getDelFlag, BaseEntity.DEL_FLAG_NORMAL));
        return PageUtil.transform(dtoPage, SysFileUploadVO.class);
    }
}
