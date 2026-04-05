package com.ZhiXingLu.Server.api;

import com.ZhiXingLu.Server.biz.auth.RefreshTokenParams;
import com.ZhiXingLu.Server.biz.auth.service.AuthService;
import com.ZhiXingLu.Server.biz.auth.model.params.UserSignInParams;
import com.ZhiXingLu.Server.biz.auth.model.vo.LoginUserVO;
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
 * @Description: 认证接口
 * @Create: 2026/4/5 18:50
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "认证接口")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    @Operation(summary = "用户登录", description = "使用账号、密码登录，返回 Token")
    public BaseResponse<LoginUserVO> signIn(@Valid @RequestBody UserSignInParams params) {
        LoginUserVO result = authService.signIn(params);
        return BaseResult.success(result);
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "刷新令牌", description = "使用 RefreshToken 获取新的 Token")
    public BaseResponse<LoginUserVO> refreshToken(@Valid @RequestBody RefreshTokenParams params) {
        LoginUserVO result = authService.refreshToken(params);
        return BaseResult.success(result);
    }
}
