package com.tiehca.apitest.heshang.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tiehca.apitest.heshang.Dao.ProductDao;
import com.tiehca.apitest.heshang.bean.Do.Product;
import com.tiehca.apitest.heshang.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author chen9
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getProductList(Integer pageNum, Integer pageSize) {

        return productDao.findByPage(pageNum,pageSize, Product.class);
    }

    @Override
    public List<Product> searchProduct(Integer pageNum, Integer pageSize, String productName, String productDesc) {
        Pattern pattern;
        JSONObject searchConfig = new JSONObject();
        if (productName != null && !"".equals(productName.trim())) {
            pattern = Pattern.compile("^.*" + productName + ".*$", Pattern.CASE_INSENSITIVE);
            searchConfig.put("name", pattern);
        } else {
            pattern = Pattern.compile("^.*" + productDesc + ".*$", Pattern.CASE_INSENSITIVE);
            searchConfig.put("desc", pattern);
        }


        return productDao.findByPage(pageNum, pageSize, Product.class,searchConfig);

    }

    @Override
    public Product addProduct(Product product) {

        return productDao.add(product);
    }

    @Override
    public boolean updateProduct(Product product) {

        Product update = productDao.update(product, product.getProductId());
        return update != null;
    }

    @Override
    public boolean updateStatus(Product product) {

        Product update = productDao.update(product, product.getProductId());
        return update != null;
    }
}
