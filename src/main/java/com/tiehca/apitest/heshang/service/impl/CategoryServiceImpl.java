package com.tiehca.apitest.heshang.service.impl;

import com.tiehca.apitest.heshang.Dao.CategoryDao;
import com.tiehca.apitest.heshang.bean.Do.Category;
import com.tiehca.apitest.heshang.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chen9
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getCategoryList(String parentId) {
        Category queryConfig = new Category();
        queryConfig.setParentId(parentId);

        return categoryDao.findByExample(queryConfig);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryDao.add(category);
    }

    @Override
    public boolean updateCategory(Category category) {
        Category update = categoryDao.update(category, category.getCategoryId());

        return update != null;
    }

    @Override
    public Category getCategory(String categoryId) {
        return categoryDao.findById(categoryId, Category.class);
    }
}
