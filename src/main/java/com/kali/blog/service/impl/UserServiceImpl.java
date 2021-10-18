package com.kali.blog.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kali.blog.common.base.BaseDTO;
import com.kali.blog.common.base.BaseEntity;
import com.kali.blog.common.base.BaseServiceImpl;
import com.kali.blog.common.context.UserContextUtil;
import com.kali.blog.common.enums.RoleEnums;
import com.kali.blog.common.util.Md5Utils;
import com.kali.blog.dto.UserDTO;
import com.kali.blog.entity.Role;
import com.kali.blog.entity.User;
import com.kali.blog.mapper.RoleMapper;
import com.kali.blog.mapper.UserMapper;
import com.kali.blog.service.UserService;
import com.kali.blog.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 用户表 服务实现类
 *
 * @author Elliot
 */
@Slf4j
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, UserDTO> implements UserService {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 创建用户
     *
     * @param userDTO 用户信息
     * @return 创建结果
     */
    @Override
    public int register(UserDTO userDTO) {
        /*从服务上下文中获取创建者ID(如果不存在，则证明是用户个人进行的注册)*/
        String createBy = UserContextUtil.getUserInfo() == null ? "123456789" : UserContextUtil.getUserInfo().getId();
        userDTO.setCreateBy(createBy);
        if (StringUtils.isBlank(userDTO.getPassword())) {
            /*如果没有填写密码，则设置初始密码为111111*/
            userDTO.setPassword(Md5Utils.md5Hex("111111"));
        } else {
            userDTO.setPassword(Md5Utils.md5Hex(userDTO.getPassword()));
        }
        /*如未选择角色 用户注册时默认为普通用户*/
        Role role = roleMapper.selectOne(new LambdaQueryWrapper<Role>().eq(BaseEntity::getDelFlag, BaseEntity.DEL_FLAG_NORMAL)
                .eq(Role::getCode, RoleEnums.ROLE_REGISTER_USER.getRoleCode()));
        if (StringUtils.isBlank(userDTO.getRoleId())){
            userDTO.setRoleId(role.getId());
        }
        userDTO.setYear(String.valueOf(LocalDateTime.now().getYear()));
        /*用户默认为审核状态*/
        userDTO.setStatus(BaseDTO.DEL_FLAG_AUDIT);
        int insert = insert(userDTO);
        if (insert > 0) {
            return insert;
        }
        return -1;
    }

    /**
     * 登陆接口
     *
     * @param loginVO 登陆信息
     * @return 查询结果
     */
    @Override
    public UserDTO doLogin(LoginVO loginVO) {
        UserDTO userDTO = selectOne(new LambdaQueryWrapper<User>()
                .eq(BaseEntity::getDelFlag, BaseEntity.DEL_FLAG_NORMAL)
                .eq(User::getMail, loginVO.getMailOrPhone())
                .or()
                .eq(User::getPhone, loginVO.getMailOrPhone())
        );
        if (ObjectUtil.isNull(userDTO)) {
            return null;
        }
        return userDTO;
    }
}
