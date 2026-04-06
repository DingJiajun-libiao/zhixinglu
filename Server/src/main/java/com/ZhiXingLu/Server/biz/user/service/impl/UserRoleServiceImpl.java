package com.ZhiXingLu.Server.biz.user.service.impl;

import com.ZhiXingLu.Server.biz.user.model.entity.UserRole;
import com.ZhiXingLu.Server.biz.user.service.UserRoleService;
import com.ZhiXingLu.Server.repository.UserRoleMapper;
import com.ZhiXingLu.Server.support.ErrorCode;
import com.ZhiXingLu.Server.support.utils.ThrowUtils;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author closer
 * @Description: 用户角色关联服务实现
 * @Create: 2026/4/5 18:03
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public void bindRole(Long userId, Long roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        boolean result = this.save(userRole);
        ThrowUtils.throwIf(!result, ErrorCode.INTERNAL_ERROR, "注册失败：角色绑定失败");
    }

    @Override
    public Long getRoleIdByUserId(Long userId) {
        UserRole userRole = this.mapper.findByUserId(userId);
        ThrowUtils.throwIf(Objects.isNull(userRole), ErrorCode.INTERNAL_ERROR,
                "查询角色失败：用户角色关联不存在 (userId: " + userId + ")");
        return userRole.getRoleId();
    }
}
