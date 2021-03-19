package com.tiehca.apitest.heshang.controller;


import com.tiehca.apitest.heshang.bean.Do.User;
import com.tiehca.apitest.heshang.bean.dto.BaseResp;
import com.tiehca.apitest.heshang.service.UserService;
import org.springframework.web.bind.annotation.*;

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

        User addUser = userService.addUser(user);

        if (addUser != null) {
            return  BaseResp.success(user);
        }

        return BaseResp.failed("此用户已存在");
    }

    @PostMapping("manage/user/update")
    BaseResp updateUser (@RequestBody User user) {
        return  null;
    }

    @PostMapping()
    BaseResp deleteUser (@RequestBody User user){
        return  null;

    }
}
