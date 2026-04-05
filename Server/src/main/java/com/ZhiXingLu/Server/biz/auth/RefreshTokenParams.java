package com.ZhiXingLu.Server.biz.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author closer
 * @Description: 刷新令牌请求参数
 * @Create: 2026/4/5 19:39
 */
@Data
@Schema(description = "刷新令牌请求参数")
public class RefreshTokenParams implements Serializable {
    @Serial
    private static final long serialVersionUID = -1188786967478166611L;

    @NotBlank
    @Schema(description = "刷新令牌")
    private String refreshToken;
}
