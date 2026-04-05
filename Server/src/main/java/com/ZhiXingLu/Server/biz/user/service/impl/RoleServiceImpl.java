package com.ZhiXingLu.Server.biz.user.service.impl;

import com.ZhiXingLu.Server.biz.user.model.entity.Role;
import com.ZhiXingLu.Server.biz.user.service.RoleService;
import com.ZhiXingLu.Server.repository.RoleMapper;
import com.ZhiXingLu.Server.support.ErrorCode;
import com.ZhiXingLu.Server.support.utils.ThrowUtils;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author closer
 * @Description: 角色服务实现
 * @Create: 2026/4/5 18:09
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public Role getByCode(String code) {
        Role role = this.mapper.findByCode(code);
        ThrowUtils.throwIf(Objects.isNull(role), ErrorCode.INTERNAL_ERROR,
                "注册失败：角色数据缺失 (roleCode: " + code + ")");
        return role;
    }
}
