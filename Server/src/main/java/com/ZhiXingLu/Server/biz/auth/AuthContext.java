package com.ZhiXingLu.Server.biz.auth;

import com.ZhiXingLu.Server.support.ErrorCode;
import com.ZhiXingLu.Server.support.exception.BusinessException;

import java.util.Objects;

/**
 * @author closer
 * @Description: 认证上下文，基于 ThreadLocal 存储当前登录用户信息
 * @Create: 2026/4/5 19:35
 */
public class AuthContext {

    private static final ThreadLocal<AuthUser> USER_HOLDER = new ThreadLocal<>();

    /**
     * 获取当前登录用户（未登录抛异常）
     */
    public static AuthUser getUser() {
        AuthUser user = USER_HOLDER.get();
        if (Objects.isNull(user)) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED, "用户未登录");
        }
        return user;
    }

    /**
     * 获取当前登录用户（未登录返回 null）
     */
    public static AuthUser getUserOrNull() {
        return USER_HOLDER.get();
    }

    /**
     * 设置当前登录用户
     */
    public static void setUser(AuthUser user) {
        USER_HOLDER.set(user);
    }

    /**
     * 清除当前登录用户（在 Filter finally 中调用）
     */
    public static void clear() {
        USER_HOLDER.remove();
    }
}

