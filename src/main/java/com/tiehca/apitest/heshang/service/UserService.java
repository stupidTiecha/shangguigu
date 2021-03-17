package com.tiehca.apitest.heshang.service;

import com.tiehca.apitest.heshang.bean.Do.User;

/**
 * @author chen9
 */
public interface UserService {

    User login(User user);

    User addUser(User user);
}
