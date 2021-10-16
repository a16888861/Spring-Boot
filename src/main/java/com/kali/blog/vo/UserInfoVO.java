package com.kali.blog.vo;

import com.kali.blog.dto.RoleDTO;
import com.kali.blog.dto.UserDTO;
import io.swagger.annotations.ApiModel;
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
    private UserDTO userDTO;

    /**
     * 角色信息
     */
    private RoleDTO roleDTO;

    /**
     * 用户Token相关信息
     */
    private UserTokenVO userTokenVO;
}
