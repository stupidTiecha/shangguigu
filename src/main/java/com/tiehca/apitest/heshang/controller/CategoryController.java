package com.tiehca.apitest.heshang.controller;

import com.tiehca.apitest.heshang.bean.Do.Category;
import com.tiehca.apitest.heshang.bean.dto.BaseResp;
import com.tiehca.apitest.heshang.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chen9
 */
@RestController("categoryController")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("manage/category/list")
    BaseResp getCategoryList (@RequestParam("parentId") String parentId) {

        List<Category> result = categoryService.getCategoryList(parentId);

        return BaseResp.success(result);
    }

    @PostMapping("manage/category/add")
    BaseResp addCategory (@RequestBody Category category) {

        Category add = categoryService.addCategory(category);

        if (add != null) {
            return BaseResp.success(add);
        } else {
            return BaseResp.failed("添加分类失败");
        }
    }

    @PostMapping("manage/category/update")
    BaseResp updateCategory (@RequestBody Category category){

        boolean status = categoryService.updateCategory(category);
        if (status) {
            return BaseResp.success();
        } else {
            return BaseResp.failed("更新分类失败");
        }
    }

    @GetMapping("manage/category/info")
    BaseResp getCategory (@RequestParam("categoryId") String categoryId) {
        Category result = categoryService.getCategory(categoryId);
        return  BaseResp.success(result);
    }
}
