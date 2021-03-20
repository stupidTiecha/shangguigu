package com.tiehca.apitest.heshang.Dao;

import com.tiehca.apitest.heshang.bean.Do.Role;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author chen
 */
@Component
public class RoleDao extends BaseDao<Role> {
    RoleDao(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }
}
