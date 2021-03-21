package com.tiehca.apitest.heshang.controller;


import com.alibaba.fastjson.JSONObject;
import com.tiehca.apitest.heshang.bean.Do.User;
import com.tiehca.apitest.heshang.bean.dto.BaseResp;
import com.tiehca.apitest.heshang.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author chen9
 */
@RestController("userController")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    BaseResp login (@RequestBody User user) {

        User login = userService.login(user);

        if (login != null) {

            return BaseResp.success(login);
        }
        return BaseResp.failed("用户名或密码不存在");
    }

    @PostMapping("manage/user/add")
    BaseResp addUser (@RequestBody User user) {

        user.setCreateTime(new Date());

        User addUser = userService.addUser(user);

        if (addUser != null) {
            return  BaseResp.success(addUser);
        }

        return BaseResp.failed("此用户已存在");
    }

    @PostMapping("manage/user/update")
    BaseResp updateUser (@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        if (updatedUser != null) {
            return  BaseResp.success(updatedUser);
        }

        return BaseResp.failed("更新用户信息失败");
    }

    @GetMapping("manage/user/list")
    BaseResp getUserList ( ) {
        JSONObject result = userService.getUserList();
        return BaseResp.success(result);
    }

    @PostMapping("manage/user/delete")
    BaseResp deleteUser (@RequestBody User user){

        boolean status = userService.deleteUser(user.getUserId());
        if (status) {
            return BaseResp.success();
        } else {
            return BaseResp.failed();
        }

    }
}
