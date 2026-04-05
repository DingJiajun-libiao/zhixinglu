package com.ZhiXingLu.Server.biz.auth;

import lombok.Data;

import java.util.List;

/**
 * @author closer
 * @Description: 认证用户信息（存入 ThreadLocal，从 JWT 中解析）
 * @Create: 2026/4/5 19:37
 */
@Data
public class AuthUser {

    /**
     * 用户 ID
     */
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 角色编码列表
     */
    private List<String> roles;
}
