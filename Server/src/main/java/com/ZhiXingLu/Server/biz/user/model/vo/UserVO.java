package com.ZhiXingLu.Server.biz.user.model.vo;

import com.ZhiXingLu.Server.biz.user.model.entity.Role;
import com.ZhiXingLu.Server.biz.user.model.entity.User;
import com.ZhiXingLu.Server.support.id.StringId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author closer
 * @Create: 2026/4/5 20:54
 */
@Data
@Schema(description = "用户信息")
public class UserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1073703920584323642L;

    @StringId
    @Schema(description = "用户ID", type = "string", example = "1234567890123456789")
    private Long id;

    @Schema(description = "账号", example = "Closer")
    private String account;

    @Schema(description = "昵称", example = "用户Closer")
    private String nickname;

    @Schema(description = "头像URL", example = "https://example.com/avatar.jpg")
    private String avatar;

    @Schema(description = "性别（0:未知 1:男 2:女）", example = "1")
    private Integer gender;

    @Schema(description = "角色名称", example = "普通用户")
    private String roleName;

    /**
     * 将 User + Role 转换为 UserVO
     *
     * @param user 用户实体
     * @param role 角色实体
     * @return UserVO
     */
    public static UserVO convert(User user, Role role) {
        UserVO vo = new UserVO();
        vo.setId(user.getId());
        vo.setAccount(user.getAccount());
        vo.setNickname(user.getNickname());
        vo.setAvatar(user.getAvatar());
        vo.setGender(user.getGender());
        vo.setRoleName(Objects.nonNull(role) ? role.getName() : null);
        return vo;
    }
}
