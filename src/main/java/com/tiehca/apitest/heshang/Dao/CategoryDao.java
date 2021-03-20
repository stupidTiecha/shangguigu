package com.tiehca.apitest.heshang.Dao;

import com.tiehca.apitest.heshang.bean.Do.Category;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author chen
 */
@Component
public class CategoryDao extends BaseDao<Category> {
    CategoryDao(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }
}
