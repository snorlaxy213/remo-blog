package com.remo.util;

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
        } else {
            if (o instanceof String) {
                if (o.toString().trim().equals("")) {
                    return true;
                }
            } else if (o instanceof List) {
                if (((List)o).size() == 0) {
                    return true;
                }
            } else if (o instanceof Map) {
                if (((Map)o).size() == 0) {
                    return true;
                }
            } else if (o instanceof Set) {
                if (((Set)o).size() == 0) {
                    return true;
                }
            } else if (o instanceof Object[]) {
                if (((Object[])o).length == 0) {
                    return true;
                }
            } else if (o instanceof int[]) {
                if (((int[])o).length == 0) {
                    return true;
                }
            } else if (o instanceof long[] && ((long[])o).length == 0) {
                return true;
            }

            return false;
        }
    }
}
