package com.kali.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.kali.springboot.common.base.BaseEntity;
import com.kali.springboot.common.base.BaseServiceImpl;
import com.kali.springboot.common.context.UserContextUtil;
import com.kali.springboot.dto.RoleDTO;
import com.kali.springboot.entity.Role;
import com.kali.springboot.mapper.RoleMapper;
import com.kali.springboot.service.RoleService;
import com.kali.springboot.vo.RoleVO;
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
