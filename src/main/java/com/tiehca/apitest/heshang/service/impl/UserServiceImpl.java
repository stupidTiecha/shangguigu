package com.tiehca.apitest.heshang.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tiehca.apitest.heshang.Dao.RoleDao;
import com.tiehca.apitest.heshang.Dao.UserDao;
import com.tiehca.apitest.heshang.bean.Do.Role;
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

    private final RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
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
        List<User> userList = userDao.findByExample(user);
        if (userList.size() == 0) {
            User add = userDao.add(user);
            return add;
        }else {
            return null;
        }
    }

    @Override
    public User updateUser(User user) {

        User update = userDao.update(user, user.getUserId());

        if (update != null){
            return update;
        }else {
            return null;
        }
    }

    @Override
    public JSONObject getUserList() {

        JSONObject result = new JSONObject();

        //用户列表，不返回admin账号
        JSONObject exclude = new JSONObject();
        exclude.put("username","admin");
        result.put("users",userDao.findByExample(new User(), exclude));

        result.put("roles",roleDao.findByExample(new Role()));

        return result;
    }
}
