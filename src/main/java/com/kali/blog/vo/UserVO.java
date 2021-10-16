package com.kali.blog.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户表VO
 *
 * @author Elliot
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "name", required = true, position = 0)
    @NotEmpty(message = "user.name.NotEmpty")
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password", required = true, position = 1)
    @NotEmpty(message = "user.password.NotEmpty")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", name = "mail", required = true, position = 2)
    @NotEmpty(message = "user.mail.NotEmpty")
    private String mail;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", name = "phone", required = true, position = 3)
    @NotEmpty(message = "user.phone.NotEmpty")
    private String phone;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", name = "roleId", hidden = true, required = false, position = 6)
    private String roleId;
}
