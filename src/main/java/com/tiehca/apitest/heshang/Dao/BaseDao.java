package com.tiehca.apitest.heshang.Dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chen9
 */
public class BaseDao<T> {

    private final MongoTemplate mongoTemplate;

     BaseDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 通过id查询对象数据
     * @param id
     * @param clazz
     * @return
     */
    public T findById(String id, Class<T> clazz) {
      return  mongoTemplate.findById(id,clazz);
    }

    /**
     * 通过对象信息进行条件查询
     * @param example
     * @return
     */
    public List<T> findByExample(T example) {
        Class<T> clazz = (Class<T>) example.getClass();
        return mongoTemplate.find(new Query().addCriteria(Criteria.byExample(example)),clazz);
    }

    /**
     * 通过id删除对象数据
     * @param id
     * @param clazz
     * @return
     */
    public DeleteResult deleteById(String id, Class<T> clazz) {
       return mongoTemplate.remove(new Query().addCriteria(Criteria.where("_id").is(id)),clazz);
    }

    /**
     * 更新数据并返回新的值
     *
     * @param newObj  需要更新的数据
     * @param id    待更新对象的id
     * @return  更新后的对象
     */
    public T update(T newObj, String id) {
        Class<T> clazz = (Class<T>) newObj.getClass();
        Document document = new Document();
        document.putAll(JSONObject.parseObject(JSON.toJSONString(newObj)));
        T updated = mongoTemplate.findAndModify(Query.query(Criteria.where("_id").is(id)), Update.fromDocument(document), FindAndModifyOptions.options().returnNew(true), clazz);

        return updated;
    }

    /**
     * 插入对象数据
     * @param obj
     * @return
     */
    public T add(T obj) {
        T insert = mongoTemplate.insert(obj);

        return insert;
    }
}
