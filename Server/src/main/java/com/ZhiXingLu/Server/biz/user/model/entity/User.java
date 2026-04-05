package com.ZhiXingLu.Server.biz.user.model.entity;

import com.ZhiXingLu.Server.support.BaseEntity;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author closer
 * @Description: 用户实体
 * @Create: 2026/4/5 17:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("user")
public class User extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -4797957827342176786L;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码（加密存储）
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像 URL
     */
    private String avatar;

    /**
     * 性别：0-未知 1-男 2-女
     */
    private Integer gender;
}
