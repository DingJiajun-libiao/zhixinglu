package com.ZhiXingLu.Server.biz.user.service;

import com.ZhiXingLu.Server.biz.user.model.entity.User;
import com.ZhiXingLu.Server.biz.user.model.params.UserSignUpParams;
import com.ZhiXingLu.Server.biz.user.model.params.UserUpdateParams;
import com.ZhiXingLu.Server.biz.user.model.vo.UserVO;
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

    /**
     * 更新个人信息
     *
     * @param params 更新请求参数（昵称、头像、性别）
     * @return 更新是否成功
     */
    Boolean update(UserUpdateParams params);

    /**
     * 获取当前登录用户信息
     *
     * @return 用户信息（含角色）
     */
    UserVO get();

}
