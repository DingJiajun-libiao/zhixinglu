package com.ZhiXingLu.Server.biz.user.service;

import com.ZhiXingLu.Server.biz.user.model.entity.User;
import com.ZhiXingLu.Server.biz.user.model.params.UserSignUpParams;
import com.mybatisflex.core.service.IService;

/**
 * @author closer
 * @Description: 用户服务接口
 * @Create: 2026/4/5 17:31
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param params 注册请求参数（账号、密码）
     * @return 注册是否成功
     */
    Boolean signUp(UserSignUpParams params);
}
