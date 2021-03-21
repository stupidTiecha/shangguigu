package com.tiehca.apitest.heshang.bean.Do;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

/**
 * @author chen9
 */
public class Category {

    @Id
    @JsonProperty("_id")
    @JSONField(serialize = false)
    private String categoryId;
    @JsonProperty("categoryName")
    private String name;

    private String parentId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
