package com.tiehca.apitest.heshang.service;

import com.alibaba.fastjson.JSONObject;
import com.tiehca.apitest.heshang.bean.Do.Product;
import com.tiehca.apitest.heshang.bean.dto.Page;

import java.util.List;

/**
 * @author chen9
 */
public interface ProductService {

    /**
     * 分页查询商品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<Product> getProductList(Integer pageNum, Integer pageSize);

    /**
     * 搜索产品列表
     *
     * @param pageNum
     * @param pageSize
     * @param productName
     * @param productDesc
     * @return
     */
    Page<Product> searchProduct(Integer pageNum, Integer pageSize, String productName, String productDesc);

    /**
     * 添加商品
     * @param product
     * @return
     */
    Product addProduct(Product product);

    /**
     * 更新产品信息
     * @param product
     * @return
     */
    boolean updateProduct(Product product);

    /**
     * 产品上架状态更新
     * @param product
     * @return
     */
    boolean updateStatus(Product product);
}
