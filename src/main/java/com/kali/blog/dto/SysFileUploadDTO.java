package com.kali.blog.dto;

import com.kali.blog.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * 系统-文件上传
 *
 * @author Elliot
 * @since 2021-10-22
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class SysFileUploadDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 文件原始名称
     */
    private String originalName;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 文件后缀(类型)
     */
    private String fileSuffix;

    /**
     * 文件相对位置
     */
    private String fileLocation;

    /**
     * 文件完整位置
     */
    private String fileFullAddress;

    /**
     * 上传者ip地址
     */
    private String ip;

    /**
     * 文件说明(备注)
     */
    private String fileRemark;


}
