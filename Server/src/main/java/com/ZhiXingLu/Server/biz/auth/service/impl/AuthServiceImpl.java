package com.ZhiXingLu.Server.biz.auth.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.ZhiXingLu.Server.biz.auth.AuthUser;
import com.ZhiXingLu.Server.biz.auth.RefreshTokenParams;
import com.ZhiXingLu.Server.biz.auth.service.AuthService;
import com.ZhiXingLu.Server.biz.auth.model.params.UserSignInParams;
import com.ZhiXingLu.Server.biz.auth.model.vo.LoginUserVO;
import com.ZhiXingLu.Server.biz.user.model.entity.Role;
import com.ZhiXingLu.Server.biz.user.model.entity.User;
import com.ZhiXingLu.Server.biz.user.model.entity.UserRole;
import com.ZhiXingLu.Server.repository.RoleMapper;
import com.ZhiXingLu.Server.repository.UserMapper;
import com.ZhiXingLu.Server.repository.UserRoleMapper;
import com.ZhiXingLu.Server.support.ErrorCode;
import com.ZhiXingLu.Server.support.auth.AuthJwtProps;
import com.ZhiXingLu.Server.support.exception.BusinessException;
import com.ZhiXingLu.Server.support.utils.ThrowUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author closer
 * @Description: 认证服务实现 JWT
 * @Create: 2026/4/5 18:53
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final String USAGE_ACCESS = "access";
    private static final String USAGE_REFRESH = "refresh";

    private final AuthJwtProps jwtProps;
    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final RoleMapper roleMapper;

    @Override
    public LoginUserVO signIn(UserSignInParams params) {
        // 根据账号查询用户
        User user = userMapper.selectByAccount(params.getAccount());
        ThrowUtils.throwIf(Objects.isNull(user), ErrorCode.INVALID_ARGUMENT,
                "登录失败：账号或密码错误");
        // BCrypt 校验密码
        boolean passwordMatch = BCrypt.checkpw(params.getPassword(), user.getPassword());
        ThrowUtils.throwIf(!passwordMatch, ErrorCode.INVALID_ARGUMENT,
                "登录失败：账号或密码错误");
        return buildLoginUserVO(user);
    }

    @Override
    public LoginUserVO refreshToken(RefreshTokenParams params) {
        // 校验 RefreshToken 并解析 userId
        DecodedJWT decodedJWT = decodeToken(params.getRefreshToken(), USAGE_REFRESH);
        Long userId = Long.valueOf(decodedJWT.getSubject());

        // 查询用户
        User user = userMapper.selectOneById(userId);
        ThrowUtils.throwIf(Objects.isNull(user), ErrorCode.INVALID_AUTHENTICATION,
                "刷新令牌失败：用户不存在");

        // 查询角色并组装返回
        return buildLoginUserVO(user);
    }

    @Override
    public AuthUser verify(String token) {
        DecodedJWT decodedJWT = decodeToken(token, USAGE_ACCESS);
        Long userId = Long.valueOf(decodedJWT.getSubject());
        return buildAuthUser(userId);
    }

    /**
     * 组装 LoginUserVO（查询角色 + 生成双 Token）
     */
    private LoginUserVO buildLoginUserVO(User user) {
        Role role = findRoleByUserId(user.getId());

        String accessToken = generateToken(user.getId(), USAGE_ACCESS, jwtProps.getAccessTokenTtl());
        String refreshToken = generateToken(user.getId(), USAGE_REFRESH, jwtProps.getRefreshTokenTtl());

        return LoginUserVO.convert(user, role,
                accessToken, refreshToken,
                jwtProps.getAccessTokenTtl(), jwtProps.getRefreshTokenTtl());
    }

    private String generateToken(Long userId, String usage, Long ttl) {
        Algorithm algo = Algorithm.HMAC256(jwtProps.getSecret());
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuer(jwtProps.getIssuer())
                .withExpiresAt(new Date(System.currentTimeMillis() + ttl))
                .withClaim("for", usage)
                .sign(algo);
    }

    private DecodedJWT decodeToken(String token, String expectedUsage) {
        DecodedJWT decodedJWT;
        try {
            Algorithm algo = Algorithm.HMAC256(jwtProps.getSecret());
            decodedJWT = JWT.require(algo)
                    .withIssuer(jwtProps.getIssuer())
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            throw new BusinessException(ErrorCode.INVALID_AUTHENTICATION, "认证令牌无效：" + e.getMessage());
        }

        String usage = decodedJWT.getClaim("for").asString();
        if (!expectedUsage.equalsIgnoreCase(usage)) {
            throw new BusinessException(ErrorCode.INVALID_AUTHENTICATION, "认证令牌类型不匹配");
        }

        return decodedJWT;
    }

    /**
     * 根据用户 ID 查询角色
     */
    private Role findRoleByUserId(Long userId) {
        UserRole userRole = userRoleMapper.findByUserId(userId);
        if (Objects.nonNull(userRole)) {
            return roleMapper.selectOneById(userRole.getRoleId());
        }
        return null;
    }

    private AuthUser buildAuthUser(Long userId) {
        User user = userMapper.selectOneById(userId);
        if (Objects.isNull(user)) {
            throw new BusinessException(ErrorCode.INVALID_AUTHENTICATION, "认证用户不存在");
        }

        AuthUser authUser = new AuthUser();
        authUser.setId(user.getId());
        authUser.setAccount(user.getAccount());

        // 查询用户角色
        Role role = findRoleByUserId(userId);
        if (Objects.nonNull(role)) {
            authUser.setRoles(List.of(role.getCode()));
        }

        return authUser;
    }
}
