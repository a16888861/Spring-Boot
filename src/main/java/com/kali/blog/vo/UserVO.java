package com.kali.blog.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "user.name.NotEmpty")
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password", required = true, position = 1)
    @NotBlank(message = "user.password.NotEmpty")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", name = "mail", required = true, position = 2)
    @NotBlank(message = "user.mail.NotEmpty")
    @Email(message = "user.mail.NotMail")
    private String mail;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", name = "phone", required = true, position = 3)
    @NotBlank(message = "user.phone.NotEmpty")
    @Length(min = 11, max = 11, message = "user.phone.NotPhone")
    private String phone;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", name = "roleId", hidden = true, required = false, position = 6)
    private String roleId;
}
