package com.kali.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kali.blog.common.base.BaseEntity;
import com.kali.blog.common.base.BaseServiceImpl;
import com.kali.blog.common.base.CommonPage;
import com.kali.blog.common.context.UserContextUtil;
import com.kali.blog.common.enums.RoleEnums;
import com.kali.blog.common.util.PageUtil;
import com.kali.blog.dto.RoleDTO;
import com.kali.blog.entity.Role;
import com.kali.blog.mapper.RoleMapper;
import com.kali.blog.service.RoleService;
import com.kali.blog.vo.RoleVO;
import com.kali.blog.vo.RoleVOPage;
import org.apache.commons.lang3.StringUtils;
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
        roleDTO.setStatus(RoleEnums.getRoleCodeByRoleEnName(roleDTO.getStatus()));
        return insert(roleDTO);
    }

    /**
     * 查询角色列表
     *
     * @param roleVoPage 查询条件
     * @return 查询结果
     */
    @Override
    public CommonPage<RoleDTO> selectRolePage(RoleVOPage roleVoPage) {
        Page<RoleDTO> page = new Page<>();
        page.setSize(roleVoPage.getPageSize()).setCurrent(roleVoPage.getPageCurrent());
        Page<RoleDTO> roleDtoPage = selectPage(page, new LambdaQueryWrapper<Role>()
                .eq(BaseEntity::getDelFlag, BaseEntity.DEL_FLAG_NORMAL)
                .eq(StringUtils.isNotBlank(roleVoPage.getStatus()), Role::getStatus, RoleEnums.getRoleCodeByRoleEnName(roleVoPage.getStatus()))
                .like(StringUtils.isNotBlank(roleVoPage.getName()), Role::getName, roleVoPage.getName())
                .like(StringUtils.isNotBlank(roleVoPage.getEnName()), Role::getEnName, roleVoPage.getEnName())
                .like(StringUtils.isNotBlank(roleVoPage.getType()), Role::getType, roleVoPage.getType())
        );
        return PageUtil.transform(roleDtoPage, RoleDTO.class);
    }
}
