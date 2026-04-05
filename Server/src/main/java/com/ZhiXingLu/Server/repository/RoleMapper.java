package com.ZhiXingLu.Server.repository;

import com.ZhiXingLu.Server.biz.user.model.entity.Role;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author closer
 * @Description: 角色 Mapper
 * @Create: 2026/4/5 17:16
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据角色编码查询角色
     *
     * @param code 角色编码
     * @return 角色，不存在返回 null
     */
    default Role findByCode(String code) {
        QueryWrapper query = QueryWrapper.create()
                .where(Role::getCode).eq(code);
        return this.selectOneByQuery(query);
    }
}
