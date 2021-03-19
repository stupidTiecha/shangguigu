package com.tiehca.apitest.heshang.Dao;

import com.tiehca.apitest.heshang.bean.Do.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author chen9
 */
@Component
public class UserDao extends BaseDao<User>{

    protected UserDao(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

}
