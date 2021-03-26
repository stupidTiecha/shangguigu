package com.tiehca.apitest.heshang.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tiehca.apitest.heshang.Dao.ProductDao;
import com.tiehca.apitest.heshang.bean.Do.Product;
import com.tiehca.apitest.heshang.bean.dto.Page;
import com.tiehca.apitest.heshang.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author chen9
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    private final Path FILE_PATH = Paths.get(System.getProperty("user.dir"));

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Page<Product> getProductList(Integer pageNum, Integer pageSize) {

        return productDao.findByPage(pageNum,pageSize, Product.class);
    }

    @Override
    public Page<Product> searchProduct(Integer pageNum, Integer pageSize, String productName, String productDesc) {
        Pattern pattern;
        JSONObject searchConfig = new JSONObject();
        if (productName != null && !"".equals(productName.trim())) {
            pattern = Pattern.compile("^.*" + productName + ".*$", Pattern.CASE_INSENSITIVE);
            searchConfig.put("name", pattern);
        } else if (productDesc != null && !"".equals(productDesc.trim())){
            pattern = Pattern.compile("^.*" + productDesc + ".*$", Pattern.CASE_INSENSITIVE);
            searchConfig.put("desc", pattern);
        } else {
            searchConfig = null;
        }

        Page<Product> result = productDao.findByPage(pageNum, pageSize, Product.class, searchConfig, "_id");

        return result;

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

    @Override
    //TODO
    public JSONObject uploadImages(MultipartFile[] files) {
        try {
           File path = new File(ResourceUtils.getURL("classpath:").getPath());
            Arrays.stream(files).forEach(file -> {
                File temp = new File(path.getAbsolutePath(), "static/upload/images");
                if (!temp.exists()) {
                    boolean mkdirs = temp.mkdirs();
                }
                try {
                    String filePath = temp.getPath() + System.currentTimeMillis() + file.getOriginalFilename();
                    File upload = new File(filePath);
                    file.transferTo(upload);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
