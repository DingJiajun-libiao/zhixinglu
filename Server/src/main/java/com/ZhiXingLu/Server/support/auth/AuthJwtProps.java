package com.ZhiXingLu.Server.support.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author closer
 * @Description: JWT 配置属性
 * @Create: 2026/4/5 19:17
 */
@Component
@ConfigurationProperties(prefix = "app.auth.jwt")
@Data
public class AuthJwtProps {

    /**
     * JWT 签发者
     */
    private String issuer;

    /**
     * JWT 密钥
     */
    private String secret;

    /**
     * AccessToken 有效期（毫秒）
     */
    private Long accessTokenTtl;

    /**
     * RefreshToken 有效期（毫秒）
     */
    private Long refreshTokenTtl;
}
