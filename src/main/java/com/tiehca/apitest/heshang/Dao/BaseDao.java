package com.tiehca.apitest.heshang.Dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author chen9
 */
@SuppressWarnings("all")
public class BaseDao<T> {

    private final MongoTemplate mongoTemplate;

    BaseDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 通过id查询对象数据
     *
     * @param id
     * @param clazz
     * @return
     */
    public T findById(String id, Class<T> clazz) {
        return mongoTemplate.findById(id, clazz);
    }

    /**
     * 通过对象信息进行条件查询
     *
     * @param example
     * @return
     */
    public List<T> findByExample(T example) {
        Class<T> clazz = (Class<T>) example.getClass();
        return mongoTemplate.find(new Query().addCriteria(Criteria.byExample(example)), clazz);
    }

    /**
     * 通过对象信息进行条件查询
     *
     * @param example
     * @param exculedValues 需要进行排除的值
     * @return
     */
    public List<T> findByExample(T example, JSONObject exculedValues) {
        Class<T> clazz = (Class<T>) example.getClass();
        Criteria criteria = Criteria.byExample(example);
        List<Criteria> criteriaList = new ArrayList<>();
        exculedValues.forEach((k, v) -> {
            Criteria temp = new Criteria();
            if (v instanceof List) {
                ((List) v).forEach(t -> {
                    Criteria ne = temp.where(k).ne(t);
                    criteriaList.add(ne);
                });
            } else {
                Criteria ne = temp.where(k).ne(v);
                criteriaList.add(ne);
            }
        });

        criteria.andOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));

        return mongoTemplate.find(new Query().addCriteria(criteria), clazz);
    }

    /**
     * 通过条件进行分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param example
     * @return
     */
    public List<T> findByPage (Integer pageNum, Integer pageSize, Class<T> clazz) {


        List<T> result = mongoTemplate.find(new Query()
                .with(Sort.by(Sort.DEFAULT_DIRECTION.DESC, "_id"))
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize), clazz);

        return result;
    }

    /**
     * 通过条件进行分页查询(模糊查询)
     *
     * @param pageNum
     * @param pageSize
     * @param example
     * @param searchConfig
     * @param orderBy
     * @return
     */
    public List<T> findByPage (Integer pageNum, Integer pageSize, Class<T> clazz, JSONObject searchConfig, String... orderBy) {

        Criteria criteria = new Criteria();
        List<Criteria> criteriaList = new ArrayList<>();
        searchConfig.forEach((k,v) -> {
            Criteria temp = new Criteria();
            if (v instanceof List) {
                ((List<?>) v).forEach( t -> {
                    temp.where(k).regex((Pattern) t);
                    criteriaList.add(temp);
                });
            } else {
                temp.where(k).regex((Pattern) v);
                criteriaList.add(temp);
            }
        });
        criteria.andOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));

        List<T> result = mongoTemplate.find(Query.query(criteria)
                .with(Sort.by(Sort.DEFAULT_DIRECTION.DESC, orderBy))
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize), clazz);

        return result;
    }

    /**
     * 条件查询（分页查询 精确查询）
     * @param pageNum
     * @param pageSize
     * @param example
     * @return
     */
    public  List<T> findByPage (Integer pageNum, Integer pageSize, T example) {
        return findByPage(pageNum, pageSize,example, "_id");
    }

    /**
     * 条件查询（分页查询 精确查询）
     * @param pageNum
     * @param pageSize
     * @param example
     * @param orderBy
     * @return
     */
    public  List<T> findByPage (Integer pageNum, Integer pageSize, T example, String... orderBy) {
        Class<T> clazz = (Class<T>) example.getClass();

        List<T> result = mongoTemplate.find(Query.query(Criteria.byExample(example))
                .with(Sort.by(Sort.DEFAULT_DIRECTION.DESC, orderBy))
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize), clazz);

        return result;
    }

    /**
     * 通过id删除对象数据
     *
     * @param id
     * @param clazz
     * @return
     */
    public DeleteResult deleteById(String id, Class<T> clazz) {
        return mongoTemplate.remove(new Query().addCriteria(Criteria.where("_id").is(id)), clazz);
    }

    /**
     * 更新数据并返回新的值
     *
     * @param newObj 需要更新的数据
     * @param id     待更新对象的id
     * @return 更新后的对象
     */
    public T update(T newObj, String id) {
        Class<T> clazz = (Class<T>) newObj.getClass();
        Document document = new Document();
        document.putAll(JSONObject.parseObject(JSON.toJSONString(newObj)));
        T updated = mongoTemplate.findAndModify(
                Query.query(Criteria.where("_id").is(id)),
                Update.fromDocument(document),
                FindAndModifyOptions.options().returnNew(true),
                clazz);

        return updated;
    }

    /**
     * 插入对象数据
     *
     * @param obj
     * @return
     */
    public T add(T obj) {
        T insert = mongoTemplate.insert(obj);

        return insert;
    }
}
