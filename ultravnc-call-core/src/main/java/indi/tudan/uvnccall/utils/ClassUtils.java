package indi.tudan.uvnccall.utils;

/**
 * 类的工具类
 *
 * @author wangtan
 * @date 2019-09-05 09:41:25
 * @since 1.0
 */
public class ClassUtils {

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