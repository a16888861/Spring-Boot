package com.kali.springboot.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kali.springboot.common.base.BaseDTO;
import com.kali.springboot.common.base.BaseEntity;
import com.kali.springboot.common.base.BaseServiceImpl;
import com.kali.springboot.common.context.UserContextUtil;
import com.kali.springboot.common.util.Md5Utils;
import com.kali.springboot.dto.UserDTO;
import com.kali.springboot.entity.User;
import com.kali.springboot.mapper.UserMapper;
import com.kali.springboot.service.UserService;
import com.kali.springboot.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        /*注册时默认为普通用户*/
//        if (StringUtil.isBlank(userDTO.getRoleId())){
//            userDTO.setRoleId();
//        }
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
