package com.ZhiXingLu.Server.biz.user.model.entity;

import com.ZhiXingLu.Server.support.BaseEntity;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author closer
 * @Description: 用户角色关联实体
 * @Create: 2026/4/5 17:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("user_role")
public class UserRole extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户 ID
     */
    private Long userId;

    /**
     * 角色 ID
     */
    private Long roleId;
}
