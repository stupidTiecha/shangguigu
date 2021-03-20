package com.tiehca.apitest.heshang.service;

import com.tiehca.apitest.heshang.bean.Do.User;

/**
 * @author chen9
 */
public interface UserService {

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 新增用户
     * @param user
     * @return
     */
    User addUser(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    User updateUser(User user);
}
