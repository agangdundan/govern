package xyz.frt.govern.service;

import org.springframework.web.multipart.MultipartFile;
import xyz.frt.govern.common.PageInfo;
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

    Integer insertItems(List<T> items);

    T selectByPrimaryKey(Integer id);

    T updateByPrimaryKeySelective(T item);

    T updateByPrimaryKey(T item);

    List<T> selectAll();

    List<T> selectAll(String orderBy);

    PageInfo<T> selectAllPage(Integer page, Integer limit);

    PageInfo<T> selectAllPage(Integer page, Integer limit, String orderBy);

    List<T> selectByConditions(Map<String, Object> map);

    List<T> selectByConditions(Map<String, Object> map, String orderBy);

    PageInfo<T> selectByConditionsPage(Map<String, Object> map, Integer page, Integer limit);

    PageInfo<T> selectByConditionsPage(Map<String, Object> map, String orderBy, Integer page, Integer limit);

    List<T> updateByConditions(Map<String, Object> map);

    T selectByUnique(String col, Object value);

    Integer selectCount();

    Integer selectCountByConditions(Map<String, Object> map);

    String[] filesUpload(String path, List<MultipartFile> files);

    String fileUpload(String path, MultipartFile file);

}
