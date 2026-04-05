package com.ZhiXingLu.Server.biz.user.service;

import com.ZhiXingLu.Server.biz.user.model.entity.UserRole;
import com.mybatisflex.core.service.IService;

/**
 * @author closer
 * @Description: 用户角色关联服务接口
 * @Create: 2026/4/5 18:02
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 绑定用户与角色
     *
     * @param userId 用户 ID
     * @param roleId 角色 ID
     */
    void bindRole(Long userId, Long roleId);
}
