package com.tiehca.apitest.heshang.service;

import com.tiehca.apitest.heshang.bean.Do.Category;

import java.util.List;

/**
 * @author chen9
 */
public interface CategoryService {
    /**
     * 通过parentId 获取该层级所有分类
     * @param parentId
     * @return
     */
    List<Category> getCategoryList(String parentId);

    /**
     * 添加分类
     * @param category
     * @return
     */
    Category addCategory(Category category);

    /**
     * 更新分类信息
     * @param category
     * @return
     */
    boolean updateCategory(Category category);

    /**
     * 通过id获取分类信息
     * @param categoryId
     * @return
     */
    Category getCategory(String categoryId);
}
