package com.tiehca.apitest.heshang.controller;

import com.tiehca.apitest.heshang.bean.Do.Product;
import com.tiehca.apitest.heshang.bean.dto.BaseResp;
import com.tiehca.apitest.heshang.bean.dto.Page;
import com.tiehca.apitest.heshang.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author chen9
 */
@RestController("productController")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("manage/product/list")
    BaseResp getProductList (@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return BaseResp.success(productService.getProductList(pageNum, pageSize));
    }

    @GetMapping("manage/product/search")
    BaseResp searchProduct (@RequestParam("pageNum") Integer pageNum,
                            @RequestParam("pageSize") Integer pageSize,
                            @RequestParam(value = "productName", required = false) String productName,
                            @RequestParam(value = "productDesc", required = false) String productDesc
                            ) {
        Page<Product> result = productService.searchProduct(pageNum, pageSize, productName, productDesc);

        return BaseResp.success(result);
    }

    @PostMapping("manage/product/add")
    BaseResp addProduct (@RequestBody Product product) {

        Product add = productService.addProduct(product);
        if (add != null) {
            return BaseResp.success(add);
        } else {
            return BaseResp.failed("添加产品异常，请稍后尝试");
        }
    }

    @PostMapping("manage/product/update")
    BaseResp updateProduct (@RequestBody Product product) {

       boolean result = productService.updateProduct(product);
       if (result) {
           return BaseResp.success();
       } else {
           return BaseResp.failed("产品信息更新异常，请稍后尝试");
       }
    }
    @PostMapping("manage/product/updateStatus")
    BaseResp updateStatus (@RequestBody Product product) {

        boolean result = productService.updateStatus(product);

        if (result) {
            return BaseResp.success();
        } else {
            return BaseResp.failed("产品状态更新异常，请稍后尝试");
        }
    }

    /**
     * TODO
     * @return
     */
    @PostMapping("manage/img/upload")
    BaseResp uploadImage(@RequestParam("images") MultipartFile...  files) {

        productService.uploadImages(files);
        return BaseResp.success("手术很成功");
    }

    /**
     * TODO
     * @return
     */
    @PostMapping("manage/img/delete")
    BaseResp deleteImage() {
        return BaseResp.success();
    }
}
