package com.kali.blog.service.impl;

import com.kali.blog.common.base.BaseServiceImpl;
import com.kali.blog.dto.SysFileUploadDTO;
import com.kali.blog.entity.SysFileUpload;
import com.kali.blog.mapper.SysFileUploadMapper;
import com.kali.blog.service.SysFileUploadService;
import org.springframework.stereotype.Service;

/**
 * 系统-文件上传 服务实现类
 *
 * @author Elliot
 * @since 2021-10-22
 */
@Service
public class SysFileUploadServiceImpl extends BaseServiceImpl<SysFileUploadMapper, SysFileUpload, SysFileUploadDTO> implements SysFileUploadService {

}
