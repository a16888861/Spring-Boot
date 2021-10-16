package com.kali.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.kali.blog.common.base.BaseEntity;
import com.kali.blog.common.base.BaseServiceImpl;
import com.kali.blog.common.context.UserContextUtil;
import com.kali.blog.dto.RoleDTO;
import com.kali.blog.entity.Role;
import com.kali.blog.mapper.RoleMapper;
import com.kali.blog.service.RoleService;
import com.kali.blog.vo.RoleVO;
import org.springframework.stereotype.Service;

/**
 * 系统-角色表 服务实现类
 *
 * @author Elliot
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role, RoleDTO> implements RoleService {

    /**
     * 创建角色
     *
     * @param roleVO 角色信息
     * @return 创建结果
     */
    @Override
    public int createRole(RoleVO roleVO) {
        RoleDTO roleDTO = BeanUtil.copyProperties(roleVO, RoleDTO.class);
        roleDTO.setCreateBy(UserContextUtil.getUserInfo().getId());
        roleDTO.setStatus(BaseEntity.DEL_FLAG_NORMAL);
        return insert(roleDTO);
    }

}
