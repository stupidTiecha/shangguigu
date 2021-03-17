package com.tiehca.apitest.heshang.bean.Do;

import java.util.List;

/**
 * @author chen9
 */
public class Product {

    private String categoryId;

    private String pCategoryId;

    private String name;

    private Integer price;

    private String desc;

    private Integer status;

    private List<String> imgs;

    private String detail;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getpCategoryId() {
        return pCategoryId;
    }

    public void setpCategoryId(String pCategoryId) {
        this.pCategoryId = pCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
