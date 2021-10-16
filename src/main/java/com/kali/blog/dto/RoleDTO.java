package com.kali.blog.dto;

import com.kali.blog.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 系统-角色表
 *
 * @author Elliot
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class RoleDTO extends BaseDTO implements Serializable {

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
}
