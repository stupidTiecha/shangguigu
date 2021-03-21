package com.tiehca.apitest.heshang.service;

import com.tiehca.apitest.heshang.bean.Do.Role;

import java.util.List;

/**
 * @author chen9
 */
public interface RoleService {

    /**
     * 添加角色
     * @param role
     * @return
     */
    Role addRole(Role role);

    /**
     * 获取全部角色列表
     * @return
     */
    List<Role> getRoleList();

    /**
     * 更新角色权限
     * @param role
     * @return
     */
    Role updateRole(Role role);
}
