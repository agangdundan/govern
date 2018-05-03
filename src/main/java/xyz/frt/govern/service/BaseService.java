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

    /**
     * 根据主键查找
     * @param id 主键
     * @return 记录
     */
    T selectByPrimaryKey(Integer id);

    /**
     * 使用非空属性更新记录
     * @param item 包含id的记录
     * @return 更新后的记录
     */
    T updateByPrimaryKeySelective(T item);

    /**
     * 更新记录
     * @param item 包含id的记录
     * @return 更新后的记录
     */
    T updateByPrimaryKey(T item);

    /**
     * 选择所有记录
     * @return 记录列表
     */
    List<T> selectAll();

    /**
     * 选择所有记录并排序
     * @param orderBy 排序列名
     * @return 排序后的记录列表
     */
    List<T> selectAll(String orderBy);

    /**
     * 分页查询记录
     * @param page 当前页
     * @param limit 每页显示数
     * @return 每页显示的记录
     */
    PageInfo<T> selectAllPage(Integer page, Integer limit);

    /**
     * 分页查询记录并排序
     * @param page 当前页
     * @param limit 每页显示数
     * @param orderBy 排序列名
     * @return 每页显示的记录
     */
    PageInfo<T> selectAllPage(Integer page, Integer limit, String orderBy);

    /**
     * 根据条件查询记录
     * @param map 条件
     * @return 符合条件的记录
     */
    List<T> selectByConditions(Map<String, Object> map);

    /**
     * 根据条件查询记录并排序
     * @param map 条件
     * @param orderBy 排序列名
     * @return 符合条件并排序后的记录
     */
    List<T> selectByConditions(Map<String, Object> map, String orderBy);

    /**
     * 根据条件进行分页查询
     * @param map 条件
     * @param page 当前页
     * @param limit 每页显示的数量
     * @return 每页显示的数据
     */
    PageInfo<T> selectByConditionsPage(Map<String, Object> map, Integer page, Integer limit);

    /**
     * 根据条件进行分页查询并排序
     * @param map 条件
     * @param orderBy 排序列名
     * @param page 当前页
     * @param limit 每页显示的数量
     * @return 每页显示的数据
     */
    PageInfo<T> selectByConditionsPage(Map<String, Object> map, String orderBy, Integer page, Integer limit);

    /**
     * 根据条件批量更新
     * @param map 条件
     * @return 更新后的记录列表
     */
    List<T> updateByConditions(Map<String, Object> map);

    /**
     * 根据Unique列查询单个对象
     * @param col Unique列名
     * @param value 值
     * @return 记录
     */
    T selectByUnique(String col, Object value);

    /**
     * 查询所有记录的数量
     * @return 数量
     */
    Integer selectCount();

    /**
     * 根据条件查询所有记录的属相
     * @param map 条件
     * @return 符合条件的数量
     */
    Integer selectCountByConditions(Map<String, Object> map);

    /**
     * 文件批量上传
     * @param path 服务器上传文件夹路径
     * @param files 文件列表
     * @return 文件服务器地址列表
     */
    String[] filesUpload(String path, List<MultipartFile> files);

    /**
     * 文件上传
     * @param path 服务器上传文件夹路径
     * @param file 文件
     * @return 文件服务器地址
     */
    String fileUpload(String path, MultipartFile file);

}
