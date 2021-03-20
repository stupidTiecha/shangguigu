package com.tiehca.apitest.heshang.Dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author chen
 */
@Component
public class ProductDao extends BaseDao<ProductDao> {
    ProductDao(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }
}
