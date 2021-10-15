package com.kali.springboot.controller.user;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.kali.springboot.common.aspect.Log;
import com.kali.springboot.common.base.BaseController;
import com.kali.springboot.common.base.BaseEntity;
import com.kali.springboot.common.constant.CommonConstants;
import com.kali.springboot.common.constant.TokenConstant;
import com.kali.springboot.common.response.Response;
import com.kali.springboot.common.response.ResponseEnum;
import com.kali.springboot.common.response.ResponseInfo;
import com.kali.springboot.common.util.JwtUtil;
import com.kali.springboot.common.util.Md5Utils;
import com.kali.springboot.common.util.RedisUtil;
import com.kali.springboot.dto.RoleDTO;
import com.kali.springboot.dto.UserDTO;
import com.kali.springboot.service.RoleService;
import com.kali.springboot.service.UserService;
import com.kali.springboot.vo.LoginVO;
import com.kali.springboot.vo.UserInfoVO;
import com.kali.springboot.vo.UserTokenVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * 登陆信息接口
 *
 * @author Elliot
 */
@RestController
@Api(value = "登陆信息", tags = "登陆信息接口")
@ApiSupport(order = 1, author = "Elliot")
@Slf4j
@RequestMapping("access")
public class AccessController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 登陆接口
     *
     * @param loginVO       登陆信息
     * @param bindingResult 参数校验
     * @return 登陆结果
     */
    @PostMapping("doLogin")
    @ApiOperation(value = "用户登陆", produces = "application/json",
            notes = "登陆用的接口<br>" +
                    "用户名(邮箱或手机号) + 密码 进行验证<br>" +
                    "生成token信息返回<br>" +
                    "管理员：<br>" +
                    "mailOrPhone: admin@mail.com<br>" +
                    "password: TOBENO.1")
    @ApiOperationSupport(author = "Elliot", order = 1)
    public ResponseInfo<UserTokenVO> doLogin(@Valid @RequestBody LoginVO loginVO, BindingResult bindingResult) {
        log.info("登陆信息为：" + loginVO);
        /*信息校验*/
        if (bindingResult.hasErrors()) {
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        /*密码加密*/
        loginVO.setPassword(Md5Utils.md5Hex(loginVO.getPassword()));
        UserDTO userDTO = userService.doLogin(loginVO);
        /*如果用户信息为空，返回用户未找到*/
        if (ObjectUtil.isNull(userDTO)) {
            return Response.notFound("user.isNull");
        }
        /*如果用户状态是1，则代表用户被锁定，返回对应信息*/
        if (userDTO.getStatus().equals(BaseEntity.DEL_FLAG_DELETE)) {
            return Response.custom(ResponseEnum.USER_LOCK.getCode(), ResponseEnum.USER_LOCK.getMessage(), null);
        }
        /*密码不一致，返回错误信息*/
        if (!userDTO.getPassword().equals(loginVO.getPassword())) {
            return Response.fail("user.password.IsError");
        }
        /*打印用户信息*/
        log.info("登陆成功，用户信息为：" + userDTO);
        /*token*/
        String token = JwtUtil.createToken(TokenConstant.USER_TOKEN + CommonConstants.HORIZONTAL_BAR + userDTO.getPhone() + CommonConstants.HORIZONTAL_BAR + userDTO.getMail());
        /*token过期时间(30分钟)*/
        long tokenExpiredTime = TimeUnit.SECONDS.toSeconds(TokenConstant.TOKEN_EXPIRATION_TIME);
        /*刷新token*/
        String refreshToken = JwtUtil.createToken(TokenConstant.USER_REFRESH_TOKEN + CommonConstants.HORIZONTAL_BAR + userDTO.getId());
        /*刷新token过期时间(2小时)*/
        long refreshTokenExpiredTime = TimeUnit.SECONDS.toSeconds(TokenConstant.REFRESH_TOKEN_EXPIRATION_TIME);
        UserTokenVO userTokenVO = UserTokenVO.builder()
                .token(token).tokenExpiredTime((int) tokenExpiredTime)
                .refreshToken(refreshToken).refreshTokenExpiredTime((int) refreshTokenExpiredTime)
                .build();
        /*Token相关信息存放到Redis中*/
        /*存储Token信息(30分钟后过期)*/
        redisUtil.set(TokenConstant.USER_TOKEN + CommonConstants.HORIZONTAL_BAR + userDTO.getPhone() + CommonConstants.HORIZONTAL_BAR + userDTO.getMail(),
                TokenConstant.TOKEN_PREFIX + CommonConstants.SPACE + token, tokenExpiredTime);
        /*存储刷新Token信息(2小时后过期)*/
        redisUtil.set(TokenConstant.USER_REFRESH_TOKEN + CommonConstants.HORIZONTAL_BAR + userDTO.getId(),
                TokenConstant.TOKEN_PREFIX + CommonConstants.SPACE + refreshToken, refreshTokenExpiredTime);
        /*登录成功后 将用户信息放到redis中(存储150分钟)*/
        redisUtil.set(TokenConstant.USER_INFO, JSONUtil.toJsonStr(userDTO), 9000);
        return Response.success(ResponseEnum.SUCCESS.getMessage(), userTokenVO);
    }

    /**
     * 获取当前登陆的用户信息
     *
     * @param request 请求
     * @return 当前登陆的用户信息
     */
    @Log("获取当前登陆的用户信息")
    @GetMapping("getUserInfo")
    @ApiOperation(value = "获取当前登陆用户信息", produces = "application/json",
            notes = "获取当前登陆用户的信息<br>" +
                    "必须登陆之后才能使用")
    @ApiOperationSupport(author = "Elliot", order = 2)
    public ResponseInfo<UserInfoVO> getUserInfoVO(HttpServletRequest request) {
        String xAuthorization = request.getHeader(TokenConstant.X_AUTHORIZATION);
        /*查询用户信息*/
        UserDTO userDTO = userService.selectById(JwtUtil.getClaim(xAuthorization.split(CommonConstants.SPACE)[1]).split(CommonConstants.HORIZONTAL_BAR)[2]);
        /*查询角色信息*/
        RoleDTO roleDTO = roleService.selectById(userDTO.getRoleId());
        UserInfoVO userInfoVO = UserInfoVO.builder()
                .userDTO(userDTO).roleDTO(roleDTO).build();
        return Response.success(ResponseEnum.SUCCESS.getMessage(), userInfoVO);
    }

    /**
     * 退出登陆
     *
     * @param request 请求
     * @return 结果
     */
    @Log("退出登陆")
    @GetMapping("logout")
    @ApiOperation(value = "退出登陆", produces = "application/json",
            notes = "退出登陆")
    @ApiOperationSupport(author = "Elliot", order = 3)
    public ResponseInfo<String> logout(HttpServletRequest request) {
        /*获取请求头授权信息(也就是token和刷新token)*/
        String authorization = request.getHeader(TokenConstant.AUTHORIZATION);
        String xAuthorization = request.getHeader(TokenConstant.X_AUTHORIZATION);
        /*删除redis中的token信息*/
        redisUtil.del(JwtUtil.getClaim(authorization.split(CommonConstants.SPACE)[1]), JwtUtil.getClaim(xAuthorization.split(CommonConstants.SPACE)[1]));
        /*删除redis中的用户信息*/
        redisUtil.del(TokenConstant.USER_INFO);
        return Response.success(ResponseEnum.SUCCESS.getMessage(), (String) null);
    }
}
