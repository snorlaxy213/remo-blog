package com.remo.userShiro.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 常用工具集合
 */
public class ToolUtil {

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        else {
            if (o instanceof String) {
                return o.toString().trim().equals("");
            }
            else if (o instanceof List) {
                return ((List) o).size() == 0;
            }
            else if (o instanceof Map) {
                return ((Map) o).size() == 0;
            }
            else if (o instanceof Set) {
                return ((Set) o).size() == 0;
            }
            else if (o instanceof Object[]) {
                return ((Object[]) o).length == 0;
            }
            else if (o instanceof int[]) {
                return ((int[]) o).length == 0;
            }
            else return o instanceof long[] && ((long[]) o).length == 0;

        }
    }
}
