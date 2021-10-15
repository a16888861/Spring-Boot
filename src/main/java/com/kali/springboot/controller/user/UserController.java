package com.kali.springboot.controller.user;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.kali.springboot.common.base.BaseController;
import com.kali.springboot.common.response.Response;
import com.kali.springboot.common.response.ResponseInfo;
import com.kali.springboot.common.util.BeanUtil;
import com.kali.springboot.dto.UserDTO;
import com.kali.springboot.service.UserService;
import com.kali.springboot.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户表Controller
 *
 * @author Elliot
 * @date 2021/10/13 14:52
 */
@Api(value = "用户信息", tags = "用户信息接口")
@ApiSupport(order = 2, author = "Elliot")
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userVO 注册信息
     * @return 注册结果
     */
    @ApiOperation(value = "用户注册", produces = "application/json",
            notes = "用户注册调用的接口<br>" +
                    "如果创建参数没有加roleId，则默认为普通用户<br>" +
                    "注册后默认为审核状态")
    @ApiOperationSupport(author = "Elliot", order = 0)
    @PostMapping("register")
    public ResponseInfo<Response> register(@Valid @RequestBody @ApiParam(name = "userVO", value = "用户信息实体", required = true) UserVO userVO,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        UserDTO userDTO = BeanUtil.copyProperties(userVO, UserDTO.class);
        return judgeResult(userService.register(userDTO));
    }
}
