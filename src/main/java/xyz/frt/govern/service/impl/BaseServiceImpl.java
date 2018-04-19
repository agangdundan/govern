package xyz.frt.govern.service.impl;

import xyz.frt.govern.common.BaseUtils;
import xyz.frt.govern.dao.BaseMapper;
import xyz.frt.govern.dao.UserMapper;
import xyz.frt.govern.model.BaseEntity;
import xyz.frt.govern.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author phw
 * @date Created in 04-19-2018
 * @description
 */
@CacheConfig(cacheNames = "redis.server")
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    @Autowired
    private UserMapper userMapper;

    public abstract BaseMapper<T> getMapper();

    @Override
    @CacheEvict
    public Integer deleteByPrimaryKey(Integer id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(T item) {
        return getMapper().insert(item);
    }

    @Override
    public Integer insertSelective(T item) {
        return getMapper().insertSelective(item);
    }

    @Override
    @Cacheable
    public T selectByPrimaryKey(Integer id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    @CachePut
    public T updateByPrimaryKeySelective(T item) {
        if (BaseUtils.isNullOrEmpty(item)) {
            return null;
        }
        Integer result = getMapper().updateByPrimaryKeySelective(item);
        if (result == 0) {
            return null;
        }
        return getMapper().selectByPrimaryKey(item.getId());
    }

    @Override
    @CachePut
    public T updateByPrimaryKey(T item) {
        if (BaseUtils.isNullOrEmpty(item)) {
            return  null;
        }
        Integer result = getMapper().updateByPrimaryKey(item);
        if (result == 0) {
            return null;
        }
        return getMapper().selectByPrimaryKey(item.getId());
    }

    @Override
    @Cacheable
    public List<T> selectAll() {
        List<T> items = getMapper().selectAll();
        if (BaseUtils.isNullOrEmpty(items)) {
            return null;
        }
        return items;
    }

    @Override
    @Cacheable
    public List<T> selectAll(String orderBy) {
        List<T> items = getMapper().selectAllOrderBy(orderBy);
        if (BaseUtils.isNullOrEmpty(items)) {
            return null;
        }
        return items;
    }

    @Override
    @Cacheable
    public List<T> selectByConditions(Map<String, Object> map) {
        List<T> items = getMapper().selectByConditions(map);
        if (BaseUtils.isNullOrEmpty(items)) {
            return  null;
        }
        return items;
    }

    @Override
    @Cacheable
    public List<T> selectByConditions(Map<String, Object> map, String orderBy) {
        List<T> items = getMapper().selectByConditionsOrderBy(map, orderBy);
        if (BaseUtils.isNullOrEmpty(items)) {
            return  null;
        }
        return items;
    }

    @Override
    @CachePut
    public List<T> updateByConditions(Map<String, Object> map) {
        if (BaseUtils.isNullOrEmpty(map)) {
            return null;
        }
        Integer result = getMapper().updateByConditions(map);
        if (result == 0) {
            return null;
        }
        List<T> items = getMapper().selectByConditions(map);
        if (BaseUtils.isNullOrEmpty(items)) {
            return null;
        }
        return items;
    }

    @Override
    @Cacheable
    public T selectByUnique(String col, Object value) {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put(col, value);
        List<T> items = selectByConditions(conditions);
        if (BaseUtils.isNullOrEmpty(items)) {
            return null;
        }
        return items.get(0);
    }

    @Override
    @Cacheable
    public Integer selectCount() {
        return getMapper().selectCount();
    }

    @Override
    @Cacheable
    public Integer selectCountByConditions(Map<String, Object> map) {
        return getMapper().selectCountByConditions(map);
    }




    /*@Override
    public JsonResult removeByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public JsonResult add(T item) {
        return null;
    }

    @Override
    @Cacheable
    public JsonResult findByPrimaryKey(Integer id) {
        T item = selectByPrimaryKey(id);
        if (BaseUtils.isNullOrEmpty(item)) {
            return JsonResult.warn("没有匹配的记录.");
        }
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(AppConst.KEY_DATA, item);
        return JsonResult.success("OK", dataMap);
    }

    @Override
    public JsonResult upgradeByPrimaryKey(T item) {
        return null;
    }

    @Override
    public JsonResult findAll() {
        return null;
    }

    @Override
    public JsonResult findAll(String orderBy) {
        return null;
    }

    @Override
    public JsonResult findByConditions(Map<String, Object> map, String orderBy) {
        return null;
    }

    @Override
    public JsonResult findByConditions(Map<String, Object> map) {
        return null;
    }

    @Override
    public JsonResult load(String col, Object value) {
        return null;
    }*/
}
