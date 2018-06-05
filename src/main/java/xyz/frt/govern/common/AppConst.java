package xyz.frt.govern.common;

/**
 * @author phw
 * @date Created in 04-08-2018
 * @description
 */
public class AppConst {

    public static final int RESULT_ERROR = 500;
    public static final int RESULT_SUCCESS = 200;
    public static final int RESULT_WARN = 201;

    public static final String redis_host = "localhost";
    public static final Integer redis_port = 6379;
    public static final Integer redis_timeout = 10000;
    public static final int redis_expire = 1800;
    public static final String redis_pwd = "199798";

    public static final String KEY_DATA = "data";
    public static final String KEY_AUTHORIZATION = "Authorization";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_ID = "id";
    public static final String KEY_GOV = "govern";
    public static final String KEY_PAGE_INFO = "page-info";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_CODE = "code";
    public static final String KEY_MSG = "msg";
    public static final String KEY_DATA_MAP_DATA= "dataMap[].data";
    public static final String VALUE_DOTE = ".";

    public static final String KEY_DATA_MAP = "dataMap";
    public static final String KEY_OLD_PASS = "oldPass";
    public static final String KEY_NEW_PASS = "newPass";

    public static final String KEY_PHONE = "phone";
    public static final String KEY_DATA_MAP_DOTE_DATA = "dataMap.data";
}
