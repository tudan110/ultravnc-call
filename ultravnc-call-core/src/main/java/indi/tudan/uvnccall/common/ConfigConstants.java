package indi.tudan.uvnccall.common;

/**
 * 常量类
 *
 * @author wangtan
 * @date 2019-08-30 10:35:10
 * @since 1.0
 */
public class ConfigConstants {

    /**
     * 系统配置文件的路径
     */
    public static final String CONFIG_PATH = "/config.properties";

    /**
     * UltraVNC Server 启动失败提示信息
     */
    public static final String START_ULTRAVNC_SERVER_ERROR_INFO = SystemConfig.getProperty("ultravnc.start.uvncserver.error.info");

    /**
     * UltraVNC Server 停止失败提示信息
     */
    public static final String STOP_ULTRAVNC_SERVER_ERROR_INFO = SystemConfig.getProperty("ultravnc.stop.uvncserver.error.info");

    /**
     * UltraVNC Viewer 启动失败提示信息
     */
    public static final String START_ULTRAVNC_VIEWER_ERROR_INFO = SystemConfig.getProperty("ultravnc.start.uvncviewer.error.info");

}