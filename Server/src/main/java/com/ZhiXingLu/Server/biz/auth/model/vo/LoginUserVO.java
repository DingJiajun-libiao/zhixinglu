package com.ZhiXingLu.Server.biz.auth.model.vo;

import com.ZhiXingLu.Server.biz.user.model.entity.Role;
import com.ZhiXingLu.Server.biz.user.model.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author closer
 * @Create: 2026/4/5 18:59
 */
@Data
@Schema(description = "登录用户信息")
public class LoginUserVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3864707864052874226L;

    @Schema(description = "账号", example = "Closer")
    private String account;

    @Schema(description = "昵称", example = "用户Closer")
    private String nickname;

    @Schema(description = "头像URL", example = "https://example.com/avatar.jpg")
    private String avatar;

    @Schema(description = "角色名称", example = "普通用户")
    private String roleName;

    @Schema(description = "访问令牌")
    private String accessToken;

    @Schema(description = "刷新令牌")
    private String refreshToken;

    @Schema(description = "访问令牌有效期（毫秒）", example = "3600000")
    private Long accessTokenTtl;

    @Schema(description = "刷新令牌有效期（毫秒）", example = "604800000")
    private Long refreshTokenTtl;

    /**
     * 将 User + Role + Token 信息转换为 LoginUserVO
     *
     * @param user            用户实体
     * @param role            角色实体
     * @param accessToken     访问令牌
     * @param refreshToken    刷新令牌
     * @param accessTokenTtl  访问令牌有效期
     * @param refreshTokenTtl 刷新令牌有效期
     * @return LoginUserVO
     */
    public static LoginUserVO convert(User user, Role role,
                                      String accessToken, String refreshToken,
                                      Long accessTokenTtl, Long refreshTokenTtl) {
        LoginUserVO vo = new LoginUserVO();
        vo.setAccount(user.getAccount());
        vo.setNickname(user.getNickname());
        vo.setAvatar(user.getAvatar());
        vo.setRoleName(Objects.nonNull(role) ? role.getName() : null);
        vo.setAccessToken(accessToken);
        vo.setRefreshToken(refreshToken);
        vo.setAccessTokenTtl(accessTokenTtl);
        vo.setRefreshTokenTtl(refreshTokenTtl);
        return vo;
    }
}
