package com.tiehca.apitest.heshang.service.impl;

import com.tiehca.apitest.heshang.Dao.RoleDao;
import com.tiehca.apitest.heshang.bean.Do.Role;
import com.tiehca.apitest.heshang.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chen9
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role addRole(Role role) {
        return roleDao.add(role);
    }

    @Override
    public List<Role> getRoleList() {

        return roleDao.findByExample(new Role());
    }

    @Override
    public Role updateRole(Role role) {
        return roleDao.update(role, role.getRoleId());
    }
}
