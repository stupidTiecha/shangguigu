package com.tiehca.apitest.heshang.controller;


import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController("api")
@RequestMapping("api")
public class MongodbTestController {

    private final MongoTemplate mongoTemplate;

    public MongodbTestController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("test01")
    Object test1() {
        mongoTemplate.save("{'测试' : '123456'}","9988776655");
        return "111";
    }

    @GetMapping("test02")
    Object test2() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("clusterReq");
        List<String> test = mongoTemplate.find(new Query(), String.class, "9988776655");
        stopWatch.stop();
        HashMap<String, Object> testMap = new HashMap<>(8);
        testMap.putIfAbsent("1", test);
        testMap.putIfAbsent("2",stopWatch);
        return testMap;
    }

    @GetMapping("test03")
    Object test03(@RequestParam String id) {
        mongoTemplate.remove(new Query(), "9988776655");
        return null;
    }
}
