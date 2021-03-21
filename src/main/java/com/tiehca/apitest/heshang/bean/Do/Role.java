package com.tiehca.apitest.heshang.bean.Do;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * @author chen9
 */
public class Role {

    @Id
    @JsonProperty("_id")
    @JSONField(serialize = false)
    private String roleId;

    private String name;

    @JsonProperty("auth_name")
    private String authName;

    @JsonProperty("auth_time")
    private Date authTime;

    @JsonProperty("create_time")
    private Date createTime;

    private List<Object> menus;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Object> getMenus() {
        return menus;
    }

    public void setMenus(List<Object> menus) {
        this.menus = menus;
    }
}
