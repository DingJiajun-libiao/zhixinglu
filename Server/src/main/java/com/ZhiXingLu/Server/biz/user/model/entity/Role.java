package com.ZhiXingLu.Server.biz.user.model.entity;

import com.ZhiXingLu.Server.support.BaseEntity;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author closer
 * @Description: 角色实体，对应表 role
 * @Create: 2026/4/5 17:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("role")
public class Role extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色编码（USER：普通用户，ADMIN：管理员，ROOT：超级管理员）
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 状态（ENABLED：正常，DISABLED：禁用）
     */
    private String status;
}
