package com.kali.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 文件表VO
 *
 * @author Elliot
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@ApiModel("文件相关信息")
public class SysFileUploadVOPage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", name = "pageCurrent", required = true, position = 1)
    @NotNull(message = "common.response.pageCurrent.NotEmpty")
    private Integer pageCurrent;

    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数", name = "pageSize", required = true, position = 2)
    @NotNull(message = "common.response.pageSize.NotEmpty")
    private Integer pageSize;

    /**
     * 文件原始名称
     */
    @ApiModelProperty(value = "文件原始名称", name = "originalName", position = 3)
    private String originalName;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称", name = "fileName", position = 4)
    private String fileName;

    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小", name = "fileSize", position = 5)
    private String fileSize;

    /**
     * 文件后缀
     */
    @ApiModelProperty(value = "文件后缀", name = "fileSuffix", position = 6)
    private String fileSuffix;

    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型", name = "fileType", position = 7)
    private String fileType;

    /**
     * 文件相对位置
     */
    @ApiModelProperty(value = "文件相对位置", name = "fileLocation", position = 8)
    private String fileLocation;

    /**
     * 文件完整位置
     */
    @ApiModelProperty(value = "文件完整位置", name = "fileFullAddress", position = 9)
    private String fileFullAddress;

    /**
     * 上传者ip地址
     */
    @ApiModelProperty(value = "上传者ip地址", name = "ip", position = 10)
    private String ip;

    /**
     * 文件说明(备注)
     */
    @ApiModelProperty(value = "文件说明(备注)", name = "fileRemark", position = 11)
    private String fileRemark;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者", name = "createBy", position = 12)
    protected String createBy;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期", name = "createDate", position = 13)
    protected LocalDateTime createDate;
}