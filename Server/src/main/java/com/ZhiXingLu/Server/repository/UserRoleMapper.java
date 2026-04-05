package com.ZhiXingLu.Server.repository;

import com.ZhiXingLu.Server.biz.user.model.entity.UserRole;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author closer
 * @Description: 用户角色关联 Mapper
 * @Create: 2026/4/5 17:16
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
