package xyz.frt.govern.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author phw
 * @date 04-08-2018
 * @description 所有请求返回的结果类
 */
public class JsonResult {

    private Integer code = 0;

    private String msg;

    private Map<String, Object> dataMap = new HashMap<>();

    public JsonResult() {

    }

    private JsonResult(Integer code, String msg, Map<String, Object> dataMap) {
        this.code = code;
        this.msg = msg;
        this.dataMap = dataMap;
    }

    private JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 成功提示
     * @param msg 提示消息
     * @return json result
     */
    public static JsonResult success(String msg) {
        return new JsonResult(AppConst.RESULT_SUCCESS, msg);
    }

    /**
     * 附带数据的成功提示
     * @param msg 提示消息
     * @param dataMap 返回的数据
     * @return json result
     */
    public static JsonResult success(String msg, Map<String, Object> dataMap) {
        return new JsonResult(AppConst.RESULT_SUCCESS, msg, dataMap);
    }

    /**
     * 错误提示
     * @param msg 提示消息
     * @return json result
     */
    public static JsonResult error(String msg) {
        return new JsonResult(AppConst.RESULT_ERROR, msg);
    }

    /**
     * 错误提示
     * @param code 错误码
     * @param msg 提示消息
     * @return json result
     */
    public static JsonResult error(Integer code, String msg) {
        return new JsonResult(code, msg);
    }

    /**
     * 警告信息
     * @param msg 提示消息
     * @return json result
     */
    public static JsonResult warn(String msg) {
        return new JsonResult(201, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
