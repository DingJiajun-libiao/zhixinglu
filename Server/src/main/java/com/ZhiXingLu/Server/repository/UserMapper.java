package com.ZhiXingLu.Server.repository;

import com.ZhiXingLu.Server.biz.user.model.entity.User;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author closer
 * @Description: 用户 Mapper
 * @Create: 2026/4/5 17:16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
