package com.kali.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kali.blog.entity.SysFileUpload;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统-文件上传 Mapper 接口
 *
 * @author Elliot
 * @since 2021-10-22
 */
@Mapper
public interface SysFileUploadMapper extends BaseMapper<SysFileUpload> {

}
