package com.kali.blog.service;


import com.kali.blog.common.base.BaseService;
import com.kali.blog.dto.RoleDTO;
import com.kali.blog.entity.Role;
import com.kali.blog.vo.RoleVO;

/**
 * 系统-角色表 服务类
 *
 * @author Elliot
 * @since 2021-08-24
 */
public interface RoleService extends BaseService<Role, RoleDTO> {

    /**
     * 创建角色
     *
     * @param roleVO 角色信息
     * @return 创建结果
     */
    int createRole(RoleVO roleVO);
}
