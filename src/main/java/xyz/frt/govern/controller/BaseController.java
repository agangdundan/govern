package xyz.frt.govern.controller;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import xyz.frt.govern.common.AppConst;
import xyz.frt.govern.common.BaseUtils;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.common.PageInfo;
import xyz.frt.govern.model.BaseEntity;
import xyz.frt.govern.service.BaseService;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author phw
 * @date 4-22-2018
 * @description
 * @param <T>
 */
public abstract class BaseController<T extends BaseEntity> {

    Map<String, Object> dataMap = new HashMap<>();

    abstract BaseService<T> getBaseService();

    /**
     * 查询所有记录列表
     * @return 记录列表
     */
    JsonResult findItems() {
        List<T> items = getBaseService().selectAll();
        if (BaseUtils.isNullOrEmpty(items)) {
            JsonResult.error("Didn't have any record");
        }
        dataMap = new HashMap<>();
        dataMap.put(AppConst.KEY_DATA, items);
        return JsonResult.success("Success", dataMap);
    }

    /**
     * 查询分页记录
     * @param info 分页信息
     * @return 每页显示的记录
     */
    JsonResult findItems(PageInfo info) {
        if (BaseUtils.isNullOrEmpty(info.getPage()) || BaseUtils.isNullOrEmpty(info.getLimit())) {
            return JsonResult.error("Get params error");
        }
        info = getBaseService().selectAllPage(info.getPage(), info.getLimit());
        dataMap = new HashMap<>();
        dataMap.put(AppConst.KEY_DATA, info);
        return JsonResult.success("Success", dataMap);
    }

    /**
     * 根据主键查找记录
     * @param id 主键
     * @return 记录
     */
    JsonResult findItemByPrimaryKey(Integer id) {
        T item = getBaseService().selectByPrimaryKey(id);
        if (BaseUtils.isNullOrEmpty(item)) {
            JsonResult.error("No such record");
        }
        dataMap = new HashMap<>();
        dataMap.put(AppConst.KEY_DATA, item);
        return JsonResult.success("Success", dataMap);
    }

    /**
     * 根据主键更新非空的记录
     * @param item 包含id的记录
     * @return 更新后的记录
     */
    JsonResult updateItemByPrimaryKey(T item) {
        item = getBaseService().updateByPrimaryKeySelective(item);
        if (BaseUtils.isNullOrEmpty(item)) {
            return JsonResult.error("Update error");
        }
        dataMap = new HashMap<>();
        dataMap.put(AppConst.KEY_DATA, item);
        return JsonResult.success("Success", dataMap);
    }

    /**
     * 批量上传文件
     * @param req 请求
     * @return 服务器文件地址列表
     */
    JsonResult filesUpload(ServletRequest req) {
        MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) req;
        String[] filesPath = getBaseService().filesUpload(req.getServletContext().getRealPath("/upload"), multiReq.getFiles("file"));
        if (BaseUtils.isNullOrEmpty(filesPath)) {
            return JsonResult.error("Files upload error");
        }
        dataMap = new HashMap<>();
        for (int i = 0; i < filesPath.length; i++) {
            dataMap.put("file" + i, filesPath[i]);
        }
        return JsonResult.success("Success", dataMap);
    }

    /**
     * 上传文件
     * @param req 请求
     * @param file 文件
     * @return 文件服务器地址
     */
    JsonResult fileUpload(ServletRequest req, MultipartFile file) {
        if (BaseUtils.isNullOrEmpty(file)) {
            return JsonResult.error("File upload error");
        }
        String filePath = getBaseService().fileUpload(req.getServletContext().getRealPath("/upload"), file);
        if (BaseUtils.isNullOrEmpty(filePath)) {
            return JsonResult.error("File upload error");
        }
        dataMap = new HashMap<>();
        dataMap.put(AppConst.KEY_DATA, filePath);
        return JsonResult.success("File upload successful", dataMap);
    }
}
