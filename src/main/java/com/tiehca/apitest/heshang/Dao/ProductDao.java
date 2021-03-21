package com.tiehca.apitest.heshang.Dao;

import com.tiehca.apitest.heshang.bean.Do.Product;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author chen
 */
@Component
public class ProductDao extends BaseDao<Product> {
    ProductDao(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }
}
