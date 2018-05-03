package xyz.frt.govern.dao;

import xyz.frt.govern.model.BaseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author phw
 * @date 5-3-2018
 * @description Mapper基类，定义基本的数据库操作方法
 * @param <T>
 */
public interface BaseMapper<T extends BaseEntity> {

    Integer deleteByPrimaryKey(Integer id);

    Integer insert(T item);

    Integer insertSelective(T item);

    T selectByPrimaryKey(Integer id);

    List<T> selectAll();

    List<T> selectAllOrderBy(String orderBy);

    List<T> selectAllOrderByAndSort(String orderBy, String sort);

    Integer updateByPrimaryKeySelective(T item);

    Integer updateByPrimaryKey(T item);

    List<T> selectByConditions(@Param("map") Map<String, Object> conditions);

    List<T> selectByConditionsOrderBy(@Param("map") Map<String, Object> conditions, @Param("orderBy") String orderBy);

    List<T> selectByConditionsOrderByAndSort(@Param("map") Map<String, Object> conditions, @Param("orderBy") String orderBy, @Param("sort") String sort);

    Integer updateByConditions(@Param("map") Map<String, Object> conditions);

    Integer selectCount();

    Integer selectCountByConditions(@Param("map") Map<String, Object> conditions);

}
