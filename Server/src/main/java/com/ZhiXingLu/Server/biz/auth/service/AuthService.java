package com.ZhiXingLu.Server.biz.auth.service;

import com.ZhiXingLu.Server.biz.auth.AuthUser;
import com.ZhiXingLu.Server.biz.auth.RefreshTokenParams;
import com.ZhiXingLu.Server.biz.auth.model.params.UserSignInParams;
import com.ZhiXingLu.Server.biz.auth.model.vo.LoginUserVO;

/**
 * @author closer
 * @Description: 认证服务接口
 * @Create: 2026/4/5 18:52
 */
public interface AuthService {

    /**
     * 用户登录
     *
     * @param params 登录请求参数（账号、密码）
     * @return 登录用户信息（含 Token）
     */
    LoginUserVO signIn(UserSignInParams params);

    /**
     * 刷新令牌
     *
     * @param params 刷新令牌请求参数
     * @return 新的登录用户信息（含新 Token）
     */
    LoginUserVO refreshToken(RefreshTokenParams params);

    /**
     * 校验 AccessToken 并返回认证用户
     *
     * @param token AccessToken
     * @return 认证用户信息
     */
    AuthUser verify(String token);
}
