package com.ZhiXingLu.Server.biz.user.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.ZhiXingLu.Server.biz.user.model.entity.Role;
import com.ZhiXingLu.Server.biz.user.model.entity.User;
import com.ZhiXingLu.Server.biz.user.model.enums.RoleCode;
import com.ZhiXingLu.Server.biz.user.model.params.UserSignUpParams;
import com.ZhiXingLu.Server.biz.user.service.RoleService;
import com.ZhiXingLu.Server.biz.user.service.UserRoleService;
import com.ZhiXingLu.Server.biz.user.service.UserService;
import com.ZhiXingLu.Server.repository.UserMapper;
import com.ZhiXingLu.Server.support.ErrorCode;
import com.ZhiXingLu.Server.support.utils.ThrowUtils;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
