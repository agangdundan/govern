package xyz.frt.govern.service;

import xyz.frt.govern.model.BaseEntity;

import java.util.List;
import java.util.Map;

public interface BaseService<T extends BaseEntity> {

    /**
     * 根据主键删除一条记录
     * @param id 主键
     * @return 删除记录的条数
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 插入一条记录
     * @param item 记录
     * @return 受影响的记录条数
     */
    Integer insert(T item);

    /**
     * 使用非空属性
     * 插入一条记录
     * @param item 记录
     * @return 受影响的记录条数
     */
    Integer insertSelective(T item);

    T selectByPrimaryKey(Integer id);

    T updateByPrimaryKeySelective(T item);

    T updateByPrimaryKey(T item);

    List<T> selectAll();

    List<T> selectAll(String orderBy);

    List<T> selectByConditions(Map<String, Object> map);

    List<T> selectByConditions(Map<String, Object> map, String orderBy);

    List<T> updateByConditions(Map<String, Object> map);

    T selectByUnique(String col, Object value);

    Integer selectCount();

    Integer selectCountByConditions(Map<String, Object> map);


    /*JsonResult removeByPrimaryKey(Integer id);

    JsonResult add(T item);

    JsonResult findByPrimaryKey(Integer id);

    JsonResult upgradeByPrimaryKey(T item);

    JsonResult findAll();

    JsonResult findAll(String orderBy);

    JsonResult findByConditions(Map<String, Object> map, String orderBy);

    JsonResult findByConditions(Map<String, Object> map);

    JsonResult load(String col, Object value);*/

}
