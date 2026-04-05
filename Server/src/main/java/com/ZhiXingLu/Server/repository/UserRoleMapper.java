package com.ZhiXingLu.Server.repository;

import com.ZhiXingLu.Server.biz.user.model.entity.UserRole;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author closer
 * @Description: 用户角色关联 Mapper
 * @Create: 2026/4/5 17:16
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户 ID 查询用户角色关联
     *
     * @param userId 用户 ID
     * @return 用户角色关联，不存在返回 null
     */
    default UserRole findByUserId(Long userId) {
        QueryWrapper query = QueryWrapper.create()
                .where(UserRole::getUserId).eq(userId);
        return this.selectOneByQuery(query);
    }
}
