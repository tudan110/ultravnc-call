package indi.tudan.uvnccall.utils;

import java.io.File;
import java.nio.file.Paths;

/**
 * 文件工具类
 *
 * @author wangtan
 * @date 2019-09-04 16:50:13
 * @since 1.0
 */
public class FileUtils {

    /**
     * 根据文件全路径获取文件名称
     *
     * @param filePath 文件全路径
     * @return String
     * @author wangtan
     * @date 2019-09-04 16:54:02
     * @since 1.0
     */
    public static String getFileNameByFilePath(String filePath) {
        return Paths.get(filePath).getFileName().toString();
    }

    /**
     * 判断文件是否存在
     *
     * @param file 文件路径
     * @return boolean
     * @author wangtan
     * @date 2019-09-04 16:59:49
     * @since 1.0
     */
    public static boolean isFileExists(String file) {
        return isFileExists(new File(file));
    }

    /**
     * 判断文件是否存在
     *
     * @param file 文件
     * @return boolean
     * @author wangtan
     * @date 2019-09-04 16:59:12
     * @since 1.0
     */
    public static boolean isFileExists(File file) {
        return file.exists();
    }

    /**
     * 判断文件夹是否存在
     *
     * @param file 文件夹路径
     * @return boolean
     * @author wangtan
     * @date 2019-09-04 17:01:46
     * @since 1.0
     */
    public static boolean isDirectoryExists(String file) {
        return isDirectoryExists(new File(file));
    }

    /**
     * 判断文件夹是否存在
     *
     * @param file 文件夹
     * @return boolean
     * @author wangtan
     * @date 2019-09-04 17:01:46
     * @since 1.0
     */
    public static boolean isDirectoryExists(File file) {
        if (isFileExists(file)) {
            return file.isDirectory();
        } else {
            return false;
        }
    }
}