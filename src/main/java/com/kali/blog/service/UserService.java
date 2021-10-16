package com.kali.blog.service;

import com.kali.blog.common.base.BaseService;
import com.kali.blog.dto.UserDTO;
import com.kali.blog.entity.User;
import com.kali.blog.vo.LoginVO;

/**
 * 用户表 服务类
 *
 * @author Elliot
 */
public interface UserService extends BaseService<User, UserDTO> {

    /**
     * 创建用户
     *
     * @param userDTO 用户信息
     * @return 创建结果
     */
    int register(UserDTO userDTO);

    /**
     * 登陆接口
     *
     * @param loginVO 登陆信息
     * @return 查询结果
     */
    UserDTO doLogin(LoginVO loginVO);
}
