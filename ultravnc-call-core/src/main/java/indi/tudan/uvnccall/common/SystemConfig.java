package indi.tudan.uvnccall.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取公共的配置文件
 *
 * @author wangtan
 * @date 2019-08-30 10:42:28
 * @since 1.0
 */
public class SystemConfig {

    /**
     * Properties 对象
     */
    private static Properties systemConfig = new Properties();

    static {
        try (InputStream inputStream = SystemConfig.class.getResourceAsStream(ConfigConstants.CONFIG_PATH)) {

            //读取配置文件
            systemConfig.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据配置项名称读取配置文件
     *
     * @param key 配置项名称
     * @return String 配置项值
     * @author wangtan
     * @date 2019-08-30 10:43:22
     * @since 1.0
     */
    public static String getProperty(String key) {

        // 读取配置项值
        return systemConfig.getProperty(key);
    }

    /**
     * 根据配置项名称写入配置文件
     *
     * @param key   配置项名称
     * @param value 配置项值
     * @author wangtan
     * @date 2019-08-30 10:44:32
     * @since 1.0
     */
    public static void setProperty(String key, String value) {

        // 写入配置项值
        systemConfig.setProperty(key, value);
    }

}