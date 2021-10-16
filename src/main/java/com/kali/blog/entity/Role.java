package com.kali.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kali.blog.common.base.BaseEntity;
import com.kali.blog.dto.RoleDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 系统-角色表
 *
 * @author Elliot
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@TableName("blog_role")
public class Role extends BaseEntity<Role, RoleDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色代码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色英文名称
     */
    private String enName;

    /**
     * 角色类型
     */
    private String type;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remarks;


    @Override
    public RoleDTO transDTO() {
        return RoleDTO.builder().id(id)
                .code(code)
                .name(name)
                .enName(enName)
                .type(type)
                .status(status)
                .createBy(createBy)
                .createDate(createDate)
                .updateBy(updateBy)
                .updateDate(updateDate)
                .remarks(remarks)
                .delFlag(delFlag)
                .build();
    }

    @Override
    public Role recDTO(RoleDTO dto) {
        return Role.builder().id(dto.getId())
                .code(dto.getCode())
                .name(dto.getName())
                .enName(dto.getEnName())
                .type(dto.getType())
                .status(dto.getStatus())
                .createBy(dto.getCreateBy())
                .createDate(dto.getCreateDate())
                .updateBy(dto.getUpdateBy())
                .updateDate(dto.getUpdateDate())
                .remarks(dto.getRemarks())
                .delFlag(dto.getDelFlag())
                .build();
    }
}
