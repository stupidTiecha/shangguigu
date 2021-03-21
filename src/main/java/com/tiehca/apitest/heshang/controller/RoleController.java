package com.tiehca.apitest.heshang.controller;

import com.tiehca.apitest.heshang.bean.Do.Role;
import com.tiehca.apitest.heshang.bean.dto.BaseResp;
import com.tiehca.apitest.heshang.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author chen9
 */
@RestController("roleController")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @PostMapping("manage/role/add")
    BaseResp addRole (@RequestBody Role role) {

        role.setCreateTime(new Date());
        Role add = roleService.addRole(role);
        return BaseResp.success(add);
    }

    @GetMapping("manage/role/list")
    BaseResp getRoleList() {
        List<Role> roleList = roleService.getRoleList();
        return BaseResp.success(roleList);
    }

    @PostMapping("manage/role/update")
    BaseResp updateRole (@RequestBody Role role) {
        role.setAuthTime(new Date());
        Role update = roleService.updateRole(role);
        if (update != null) {
            return BaseResp.success(update);
        } else {
            return BaseResp.failed("更新角色权限异常，请稍后尝试");
        }
    }
}
