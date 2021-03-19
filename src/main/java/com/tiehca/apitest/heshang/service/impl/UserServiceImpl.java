package com.tiehca.apitest.heshang.service.impl;

import com.tiehca.apitest.heshang.Dao.UserDao;
import com.tiehca.apitest.heshang.bean.Do.User;
import com.tiehca.apitest.heshang.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author chen9
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public User login(User user) {

        List<User> users = userDao.findByExample(user);
        if (users.size() == 1) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User addUser(User user) {
        User add = userDao.add(user);
        return add;
    }
}
