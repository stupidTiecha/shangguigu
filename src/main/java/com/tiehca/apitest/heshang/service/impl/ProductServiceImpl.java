package com.tiehca.apitest.heshang.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tiehca.apitest.heshang.Dao.ProductDao;
import com.tiehca.apitest.heshang.bean.Do.Product;
import com.tiehca.apitest.heshang.bean.dto.Page;
import com.tiehca.apitest.heshang.common.config.ServiceConfig;
import com.tiehca.apitest.heshang.common.util.PathUtil;
import com.tiehca.apitest.heshang.common.util.SequenceUtil;
import com.tiehca.apitest.heshang.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author chen9
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    private static final String IMAGE_PREFIX = "image-";
    private final Path FILE_PATH = Paths.get(System.getProperty("user.dir"));

    private final ServiceConfig config;

    private final ProductDao productDao;

    public ProductServiceImpl(ServiceConfig config, ProductDao productDao) {
        this.config = config;
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
    public List<JSONObject> uploadImages(MultipartFile[] files) {
        List<JSONObject> result = new ArrayList<>();
        File uploadPath = PathUtil.getUploadPath();
        Arrays.stream(files).parallel().forEach(file -> {

                try {
                    JSONObject temp = new JSONObject();
                    String oName = file.getOriginalFilename();
                    String fileType = oName.substring(oName.lastIndexOf("."));
                    String fileName =  IMAGE_PREFIX + System.currentTimeMillis() + SequenceUtil.getSequenceString() + fileType;
                    String filePath = uploadPath.getPath() + "/" + fileName;
                    File upload = new File(filePath);
                    file.transferTo(upload);
                    temp.put("name",fileName);
                    temp.put("url", config.getImageUrlPrefix() + fileName);
                    result.add(temp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


        return result;
    }

    @Override
    public void deleteImage(String imageName) {

        File uploadPath = PathUtil.getUploadPath();

        File deleteFile = new File(uploadPath.getPath() + "/" + imageName);

        if (deleteFile.exists()) {
            if (!deleteFile.delete()){
                throw new RuntimeException("删除图片失败");
            }
        }
    }
}
