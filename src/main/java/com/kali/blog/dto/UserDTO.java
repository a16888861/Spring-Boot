package com.kali.blog.dto;

import com.kali.blog.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 用户表DTO
 *
 * @author Elliot
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class UserDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 年份
     */
    private String year;

    /**
     * 状态(0正常,1锁定,2审批)
     */
    private String status;

}
