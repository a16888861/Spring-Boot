package com.kali.blog.entity;

import com.kali.blog.common.base.BaseEntity;
import com.kali.blog.dto.SysFileUploadDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 系统-文件上传
 *
 * @author Elliot
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class SysFileUpload extends BaseEntity<SysFileUpload, SysFileUploadDTO> {

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


    @Override
    public SysFileUploadDTO transDTO() {
        return SysFileUploadDTO.builder().id(id)
                .originalName(originalName)
                .fileName(fileName)
                .fileSize(fileSize)
                .fileSuffix(fileSuffix)
                .fileLocation(fileLocation)
                .fileFullAddress(fileFullAddress)
                .ip(ip)
                .fileRemark(fileRemark)
                .createBy(createBy)
                .createDate(createDate)
                .updateBy(updateBy)
                .updateDate(updateDate)
                .delFlag(delFlag)
                .build();
    }

    @Override
    public SysFileUpload recDTO(SysFileUploadDTO dto) {
        return SysFileUpload.builder().id(dto.getId())
                .originalName(dto.getOriginalName())
                .fileName(dto.getFileName())
                .fileSize(dto.getFileSize())
                .fileSuffix(dto.getFileSuffix())
                .fileLocation(dto.getFileLocation())
                .fileFullAddress(dto.getFileFullAddress())
                .ip(dto.getIp())
                .fileRemark(dto.getFileRemark())
                .createBy(dto.getCreateBy())
                .createDate(dto.getCreateDate())
                .updateBy(dto.getUpdateBy())
                .updateDate(dto.getUpdateDate())
                .delFlag(dto.getDelFlag())
                .build();
    }
}
