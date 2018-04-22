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

    JsonResult findItems() {
        List<T> items = getBaseService().selectAll();
        if (BaseUtils.isNullOrEmpty(items)) {
            JsonResult.error("Didn't have any record");
        }
        dataMap = new HashMap<>();
        dataMap.put(AppConst.KEY_DATA, items);
        return JsonResult.success("Success", dataMap);
    }

    JsonResult findItems(PageInfo info) {
        if (BaseUtils.isNullOrEmpty(info.getPage()) || BaseUtils.isNullOrEmpty(info.getLimit())) {
            return JsonResult.error("Get params error");
        }
        info = getBaseService().selectAllPage(info.getPage(), info.getLimit());
        dataMap = new HashMap<>();
        dataMap.put(AppConst.KEY_DATA, info);
        return JsonResult.success("Success", dataMap);
    }

    JsonResult findItemByPrimaryKey(Integer id) {
        T item = getBaseService().selectByPrimaryKey(id);
        if (BaseUtils.isNullOrEmpty(item)) {
            JsonResult.error("No such record");
        }
        dataMap = new HashMap<>();
        dataMap.put(AppConst.KEY_DATA, item);
        return JsonResult.success("Success", dataMap);
    }

    JsonResult updateItemByPrimaryKey(T item) {
        item = getBaseService().updateByPrimaryKeySelective(item);
        if (BaseUtils.isNullOrEmpty(item)) {
            return JsonResult.error("Update error");
        }
        dataMap = new HashMap<>();
        dataMap.put(AppConst.KEY_DATA, item);
        return JsonResult.success("Success", dataMap);
    }

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
