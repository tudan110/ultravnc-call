package indi.tudan.uvnccall.utils;

/**
 * 对象工具类
 *
 * @author wangtan
 * @date 2019-10-30 22:51:41
 * @since 1.0
 */
public class ObjectUtils {

    /**
     * Don't let anyone else instantiate this class
     */
    private ObjectUtils() {
    }

    /**
     * 判断对象是否为 Null
     *
     * @param obj 对象
     * @return boolean
     * @date 2020-06-02 10:28:37
     */
    public static boolean isNull(Object obj) {
        return null == obj || obj.equals(null);
    }

    /**
     * 判断对象是否为 Null
     *
     * @param obj 对象
     * @return boolean
     * @date 2019-10-30 22:52:26
     */
    public static boolean isNotNull(Object obj) {
        return null != obj && !obj.equals(null);
    }
}
