package com.kali.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kali.blog.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统-角色表 Mapper 接口
 *
 * @author Elliot
 * @since 2021-08-24
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
