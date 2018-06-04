package xyz.frt.govern.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.web.multipart.MultipartFile;
import xyz.frt.govern.common.BaseUtils;
import xyz.frt.govern.common.PageInfo;
import xyz.frt.govern.dao.BaseMapper;
import xyz.frt.govern.dao.UserMapper;
import xyz.frt.govern.model.BaseEntity;
import xyz.frt.govern.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public Integer insertItems(List<T> items) {
        if (BaseUtils.isNullOrEmpty(items)) {
            return 0;
        }
        Integer result = 0;
        for (T item: items) {
            getMapper().insertSelective(item);
            result++;
        }
        return result;
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
        if (BaseUtils.isNullOrEmpty(item.getId())) {
            return null;
        }
        if (getMapper().updateByPrimaryKeySelective(item) == 0) {
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
        if (getMapper().updateByPrimaryKey(item) == 0) {
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
    public PageInfo<T> selectAllPage(Integer page, Integer limit) {
        if (BaseUtils.isNullOrEmpty(page) || BaseUtils.isNullOrEmpty(limit)) {
            return null;
        }
        Page<T> tPage = PageHelper.startPage(page, limit);
        List<T> data = getMapper().selectAll();
        Integer totalCount = getMapper().selectCount();
        Integer totalPage = totalCount / limit;
        return new PageInfo<>(page, totalPage, limit, totalCount, data);
    }

    @Override
    @Cacheable
    public PageInfo<T> selectAllPage(Integer page, Integer limit, String orderBy) {
        if (BaseUtils.isNullOrEmpty(page) || BaseUtils.isNullOrEmpty(limit) || BaseUtils.isNullOrEmpty(orderBy)) {
            return null;
        }
        Page<T> tPage = PageHelper.startPage(page, limit);
        List<T> data = getMapper().selectAllOrderBy(orderBy);
        Integer totalCount = getMapper().selectCount();
        Integer totalPage = totalCount / limit;
        return new PageInfo<>(page, totalPage, limit, totalCount, data);
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

    @Override
    @Cacheable
    public PageInfo<T> selectByConditionsPage(Map<String, Object> map, Integer page, Integer limit) {
        if (BaseUtils.isNullOrEmpty(map) || BaseUtils.isNullOrEmpty(page) || BaseUtils.isNullOrEmpty(limit)) {
            return null;
        }
        Integer totalCount = getMapper().selectCountByConditions(map);
        if (totalCount == 0) {
            return null;
        }
        Integer totalPage = totalCount / limit;
        Page<T> tPage = PageHelper.startPage(page, limit);
        List<T> data = getMapper().selectByConditions(map);

        return new PageInfo<>(page, totalPage, limit, totalCount, data);
    }

    @Override
    @Cacheable
    public PageInfo<T> selectByConditionsPage(Map<String, Object> map, String orderBy, Integer page, Integer limit) {
        if (BaseUtils.isNullOrEmpty(map) || BaseUtils.isNullOrEmpty(page) || BaseUtils.isNullOrEmpty(limit)) {
            return null;
        }
        if (BaseUtils.isNullOrEmpty(orderBy)) {
            return null;
        }
        Integer totalCount = getMapper().selectCountByConditions(map);
        if (totalCount == 0) {
            return null;
        }
        Integer totalPage = totalCount / limit;
        Page<T> tPage = PageHelper.startPage(page, limit);
        List<T> data = getMapper().selectByConditionsOrderBy(map, orderBy);

        return new PageInfo<>(page, totalPage, limit, totalCount, data);
    }

    @Override
    public String[] filesUpload(String path, List<MultipartFile> files) {
        if (BaseUtils.isNullOrEmpty(files)) {
            return null;
        }
        String[] filesPath = new String[files.size() + 1];
        for (int i = 0; i < files.size(); i++) {
            filesPath[i] = fileUpload(path, files.get(i));
        }
        return filesPath;
    }

    @Override
    public String fileUpload(String path, MultipartFile file) {
        if (BaseUtils.isNullOrEmpty(file)) {
            return null;
        }
        String originalName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalName.substring(originalName.lastIndexOf("."), originalName.length());
        try {
            File uploadFile = new File(path, fileName);
            if (!uploadFile.getParentFile().exists()) {
                if (uploadFile.getParentFile().mkdirs()) {
                    return null;
                }
            }
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path + File.separator + fileName;
    }
}
