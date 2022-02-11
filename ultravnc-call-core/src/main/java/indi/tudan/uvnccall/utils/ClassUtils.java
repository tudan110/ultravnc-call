package indi.tudan.uvnccall.utils;

import cn.hutool.log.StaticLog;

import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.Objects;

/**
 * 类的工具类
 *
 * @author wangtan
 * @date 2019-09-28 11:07:12
 * @since 1.0
 */
public class ClassUtils {

    /**
     * Don't let anyone else instantiate this class
     */
    private ClassUtils() {
    }

    /**
     * 获取当前程序所在路径
     *
     * @return String
     * @date 2019-09-28 11:08:39
     * @since 1.0
     */
    public static String getCurrentProgramPath() {
        CodeSource codeSource = ClassUtils.class.getProtectionDomain().getCodeSource();

        File jarFile = null;
        try {
            jarFile = new File(codeSource.getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            StaticLog.error(e);
        }

        return Objects.requireNonNull(jarFile, "jar file should not be null.").getParentFile().getPath();
    }

    /**
     * 判断由提供的类名（类的全限定名）标识的类是否存在并可以加载
     * 如果类或其中一个依赖关系不存在或无法加载，则返回false
     *
     * @param className 要检查的类的名称
     * @return 指定的类是否存在
     * @author wangtan
     * @date 2019-09-05 09:43:31
     * @since 1.0
     */
    public static boolean isPresent(String className) {
        try {
            Thread.currentThread().getContextClassLoader().loadClass(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isPresent("indi.tudan.uvnccall.utils.ClassUtils"));
    }
}