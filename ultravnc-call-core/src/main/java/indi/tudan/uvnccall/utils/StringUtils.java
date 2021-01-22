package indi.tudan.uvnccall.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 *
 * @author wangtan
 * @date 2019-10-27 13:43:20
 * @since 1.0
 */
public class StringUtils {

    /**
     * Don't let anyone else instantiate this class
     */
    private StringUtils() {
    }

    /**
     * 获取字符串
     *
     * @param object 待处理数据
     * @return 处理后的字符串
     * @date 2019-10-26 17:31:49
     */
    public static String getStr(Object object) {
        if (ObjectUtils.isNotNull(object)) {
            return String.valueOf(object);
        } else {
            return "";
        }
    }

    /**
     * 字符串是否为空
     *
     * @param cs 字符串
     * @return boolean
     * @date 2019-10-26 17:31:14
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 字符串是否不为空
     *
     * @param cs 字符串
     * @return boolean
     * @date 2019-10-26 17:31:07
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * 若字符串为空，则返回 defaultString
     *
     * @param string        字符串
     * @param defaultString 默认字符串
     * @return String
     * @author wangtan
     * @date 2019-09-06 13:53:42
     * @since 1.0
     */
    public static String orElse(String string, String defaultString) {
        return isBlank(string) ? defaultString : string;
    }

    /**
     * 去除所有空白符
     *
     * @param str 字符串
     * @return
     * @date 2020-07-21 19:55:39
     * @since 1.0
     */
    public static String replaceBlank(String str) {
        String dest = null;
        if (str == null) {
            return dest;
        } else {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
            return dest;
        }
    }

}
