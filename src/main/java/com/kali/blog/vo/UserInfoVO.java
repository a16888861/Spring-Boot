package com.kali.blog.vo;

import com.kali.blog.dto.UserDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Elliot
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel("用户相关信息")
public class UserInfoVO {

    /**
     * 用户信息
     */
    @ApiModelProperty(value = "用户信息", name = "userDTO", position = 0)
    private UserDTO userDTO;

    /**
     * 角色信息
     */
    @ApiModelProperty(value = "角色信息", name = "roleDTO", position = 1)
    private RoleVO roleVO;

    /**
     * 用户Token相关信息
     */
    @ApiModelProperty(value = "用户Token相关信息", name = "userTokenVO", position = 2)
    private UserTokenVO userTokenVO;
}
