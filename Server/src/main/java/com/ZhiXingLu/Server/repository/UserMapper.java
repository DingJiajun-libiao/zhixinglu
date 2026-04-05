package com.ZhiXingLu.Server.repository;

import com.ZhiXingLu.Server.biz.user.model.entity.User;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author closer
 * @Description: 用户 Mapper
 * @Create: 2026/4/5 17:16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 判断账号是否已存在
     *
     * @param account 账号
     * @return 存在返回 true
     */
    default boolean existByAccount(String account) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(User::getAccount).eq(account);
        return this.selectCountByQuery(queryWrapper) > 0;
    }

    /**
     * 根据账号查询用户
     *
     * @param account 账号
     * @return 用户，不存在返回 null
     */
    default User selectByAccount(String account) {
        QueryWrapper query = QueryWrapper.create()
                .where(User::getAccount).eq(account);
        return this.selectOneByQuery(query);
    }
}
