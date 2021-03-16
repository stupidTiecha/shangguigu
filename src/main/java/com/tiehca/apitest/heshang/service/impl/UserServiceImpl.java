package com.tiehca.apitest.heshang.service.impl;

import com.tiehca.apitest.heshang.bean.Do.User;
import com.tiehca.apitest.heshang.service.UserService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {

    private final MongoTemplate mongoTemplate;

    public UserServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User login(User user) {

        return null;
    }

    @Override
    public User addUser(User user) {

        return null;
    }
}
