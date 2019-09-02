package indi.tudan.uvnccall.utils;

/**
 * String 工具类
 *
 * @author wangtan
 * @date 2019-08-30 13:54:17
 * @since 1.0
 */
public class StringUtils {

    /**
     * Don't let anyone else instantiate this class
     */
    private StringUtils() {
    }

    /**
     * 字符串是否为空
     *
     * @param str 字符串
     * @return boolean
     * @author wangtan
     * @date 2019-08-30 13:59:35
     * @since 1.0
     */
    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    /**
     * 字符串是否不为空
     *
     * @param str 字符串
     * @return boolean
     * @author wangtan
     * @date 2019-08-30 13:59:45
     * @since 1.0
     */
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }
}