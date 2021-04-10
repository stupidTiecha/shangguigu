package com.tiehca.apitest.heshang.controller;


import com.tiehca.apitest.heshang.bean.Do.Category;
import com.tiehca.apitest.heshang.bean.Do.Product;
import com.tiehca.apitest.heshang.bean.Do.Role;
import com.tiehca.apitest.heshang.bean.Do.User;
import com.tiehca.apitest.heshang.bean.dto.BaseResp;
import com.tiehca.apitest.heshang.common.util.PathUtil;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/**
 * @author chen9
 */
@RestController("api")
@RequestMapping("api")
public class ProjectManagerController {

    private final MongoTemplate mongoTemplate;

    public ProjectManagerController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("test01")
    Object test1() {
        User user = new User();
        user.setUsername("测试");
        user.setPassword("9988776655");
        user.setPhone("13566884545");
        user.setEmail("ceshi@tenxunshabi.com");
        user.setCreateTime(new Date());
        mongoTemplate.save(user);


        return BaseResp.success();
    }

    @GetMapping("test02")
    Object test2() {
        User user = new User();
        user.setUsername("测试");
        StopWatch stopWatch = new StopWatch();
        Query query = new Query();
        query.addCriteria(Criteria.byExample(user));
        stopWatch.start("clusterReq");
        User one = mongoTemplate.findOne(query, User.class);
        stopWatch.stop();
        HashMap<String, Object> testMap = new HashMap<>(8);
        testMap.putIfAbsent("1", one);
        testMap.putIfAbsent("2",stopWatch);
        return testMap;
    }

    @GetMapping("clearUser")
    Object clearUser() {
        mongoTemplate.remove(new Query(),User.class);
        return BaseResp.success();
    }

    @GetMapping("clearRole")
    Object clearRole() {
        mongoTemplate.remove(new Query(), Role.class);
        return BaseResp.success();
    }

    @GetMapping("clearProduct")
    Object clearProduct() {
        mongoTemplate.remove(new Query(), Product.class);
        return BaseResp.success();
    }

    @GetMapping("clearCategory")
    Object clearCategory() {
        mongoTemplate.remove(new Query(), Category.class);
        return BaseResp.success();
    }

    @GetMapping("clearImages")
    Object clearImages() {
        File uploadPath = PathUtil.getUploadPath();
        File[] files = uploadPath.listFiles();
        Arrays.stream(files).parallel()
                .forEach( file ->  {
                    file.delete();
                });

        return BaseResp.success();
    }
}
