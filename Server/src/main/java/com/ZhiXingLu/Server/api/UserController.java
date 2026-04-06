package com.ZhiXingLu.Server.api;

import com.ZhiXingLu.Server.biz.user.model.params.UserSignUpParams;
import com.ZhiXingLu.Server.biz.user.model.params.UserUpdateParams;
import com.ZhiXingLu.Server.biz.user.model.vo.UserVO;
import com.ZhiXingLu.Server.biz.user.service.UserService;
import com.ZhiXingLu.Server.support.BaseResponse;
import com.ZhiXingLu.Server.support.BaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author closer
 * @Create: 2026/4/5 17:30
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "用户接口")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @Operation(summary = "注册", description = "使用账号、密码注册")
    public BaseResponse<Boolean> signUp(@Valid @RequestBody UserSignUpParams params) {
        Boolean result = userService.signUp(params);
        return BaseResult.success(result);
    }

    @PostMapping("/update")
    @Operation(summary = "更新个人信息", description = "更新当前登录用户的昵称、头像、性别")
    public BaseResponse<Boolean> update(@Valid @RequestBody UserUpdateParams params) {
        Boolean result = userService.update(params);
        return BaseResult.success(result);
    }

    @GetMapping("/get")
    @Operation(summary = "获取用户信息", description = "获取当前登录用户详情")
    public BaseResponse<UserVO> get() {
        UserVO result = userService.get();
        return BaseResult.success(result);
    }
}
