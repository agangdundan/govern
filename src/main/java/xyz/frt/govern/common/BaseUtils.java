package xyz.frt.govern.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author phw
 * @date Created in 04-08-2018
 * @description
 */
public class BaseUtils<T> {

    /**
     * 判断一个对象是否为空
     * @param object 要判断的对象
     * @return 是否为空
     */
    public static boolean isNullOrEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            String str = (String) object;
            return str.equals("") || str.length() == 0;
        } if (object instanceof List<?>) {
            List<?> list = (List<?>) object;
            return list.size() == 0;
        } if (object instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) object;
            return map.size() == 0;
        }
        return false;
    }

}
