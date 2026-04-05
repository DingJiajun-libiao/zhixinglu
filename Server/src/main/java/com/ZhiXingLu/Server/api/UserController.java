package com.ZhiXingLu.Server.api;

import com.ZhiXingLu.Server.biz.user.model.params.UserSignUpParams;
import com.ZhiXingLu.Server.biz.user.service.UserService;
import com.ZhiXingLu.Server.support.BaseResponse;
import com.ZhiXingLu.Server.support.BaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
