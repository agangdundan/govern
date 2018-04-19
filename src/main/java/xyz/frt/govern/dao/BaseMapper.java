package xyz.frt.govern.dao;

import xyz.frt.govern.model.BaseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T extends BaseEntity> {

    Integer deleteByPrimaryKey(Integer id);

    Integer insert(T item);

    Integer insertSelective(T item);

    T selectByPrimaryKey(Integer id);

    List<T> selectAll();

    List<T> selectAllOrderBy(String orderBy);

    Integer updateByPrimaryKeySelective(T item);

    Integer updateByPrimaryKey(T item);

    List<T> selectByConditions(@Param("map") Map<String, Object> conditions);

    List<T> selectByConditionsOrderBy(@Param("map") Map<String, Object> conditions, @Param("orderBy") String orderBy);

    Integer updateByConditions(Map<String, Object> conditions);

    Integer selectCount();

    Integer selectCountByConditions(Map<String, Object> map);

}
