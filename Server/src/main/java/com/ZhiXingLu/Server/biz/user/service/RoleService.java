package com.ZhiXingLu.Server.biz.user.service;

import com.ZhiXingLu.Server.biz.user.model.entity.Role;
import com.mybatisflex.core.service.IService;

/**
 * @author closer
 * @Description: 角色服务接口
 * @Create: 2026/4/5 18:08
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据角色编码查询角色
     *
     * @param code 角色编码
     * @return 角色
     */
    Role getByCode(String code);

}
