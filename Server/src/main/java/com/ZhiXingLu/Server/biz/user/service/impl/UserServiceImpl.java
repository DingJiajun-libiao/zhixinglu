package com.ZhiXingLu.Server.biz.user.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.ZhiXingLu.Server.biz.auth.AuthContext;
import com.ZhiXingLu.Server.biz.user.model.entity.Role;
import com.ZhiXingLu.Server.biz.user.model.entity.User;
import com.ZhiXingLu.Server.biz.user.model.enums.RoleCode;
import com.ZhiXingLu.Server.biz.user.model.params.UserSignUpParams;
import com.ZhiXingLu.Server.biz.user.model.params.UserUpdateParams;
import com.ZhiXingLu.Server.biz.user.model.vo.UserVO;
import com.ZhiXingLu.Server.biz.user.service.RoleService;
import com.ZhiXingLu.Server.biz.user.service.UserRoleService;
import com.ZhiXingLu.Server.biz.user.service.UserService;
import com.ZhiXingLu.Server.repository.UserMapper;
import com.ZhiXingLu.Server.support.ErrorCode;
import com.ZhiXingLu.Server.support.utils.ThrowUtils;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author closer
 * @Description: 用户服务实现
 * @Create: 2026/4/5 17:32
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final RoleService roleService;
    private final UserRoleService userRoleService;

    @Override
    @Transactional
    public Boolean signUp(UserSignUpParams params) {
        // 是否已存在用户
        String account = params.getAccount();
        boolean existUser = this.mapper.existByAccount(account);
        ThrowUtils.throwIf(existUser, ErrorCode.INVALID_ARGUMENT,
                "注册失败：账号已注册 (phone: " + params.getAccount() + ")");

        // 加密密码并保存用户
        User user = new User();
        user.setAccount(account);
        user.setPassword(BCrypt.hashpw(params.getPassword()));
        user.setNickname("用户"+account);
        boolean saved = this.save(user);
        ThrowUtils.throwIf(!saved, ErrorCode.INTERNAL_ERROR, "注册失败：用户保存失败");

        // 查询角色
        Role role = roleService.getByCode(RoleCode.USER.name());

        // 绑定用户角色
        userRoleService.bindRole(user.getId(),role.getId());

        return true;
    }

    @Override
    public Boolean update(UserUpdateParams params) {
        // 从认证上下文获取当前登录用户 ID
        Long userId = AuthContext.getUser().getId();
        // 校验用户是否存在
        User user = this.getById(userId);
        ThrowUtils.throwIf(Objects.isNull(user), ErrorCode.NOT_FOUND,
                "更新用户失败：用户不存在 (id: " + userId + ")");

        // 仅更新非 null 字段
        if (Objects.nonNull(params.getNickname())) {
            user.setNickname(params.getNickname());
        }
        if (Objects.nonNull(params.getAvatar())) {
            user.setAvatar(params.getAvatar());
        }
        if (Objects.nonNull(params.getGender())) {
            user.setGender(params.getGender());
        }

        boolean updated = this.updateById(user);
        ThrowUtils.throwIf(!updated, ErrorCode.INTERNAL_ERROR,
                "更新用户失败：保存失败");
        return true;
    }

    @Override
    @Transactional
    public UserVO get() {
        // 从认证上下文获取当前登录用户 ID
        Long userId = AuthContext.getUser().getId();

        // 查询用户
        User user = this.getById(userId);
        ThrowUtils.throwIf(Objects.isNull(user), ErrorCode.NOT_FOUND,
                "获取用户失败：用户不存在 (id: " + userId + ")");

        // 查询用户角色
        Long roleId = userRoleService.getRoleIdByUserId(user.getId());
        Role role = roleService.getById(roleId);

        return UserVO.convert(user, role);
    }
}
