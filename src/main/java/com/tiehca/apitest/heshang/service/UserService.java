package com.tiehca.apitest.heshang.service;

import com.tiehca.apitest.heshang.bean.Do.User;

public interface UserService {

    User login(User user);

    User addUser(User user);
}
