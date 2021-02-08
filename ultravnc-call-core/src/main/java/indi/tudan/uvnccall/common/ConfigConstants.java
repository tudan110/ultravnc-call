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
     * 项目开发模式
     */
    public static final String SYSTEM_DEVELOP_MODE = SystemConfig.getProperty("system.develop.mode");

    /**
     * 开发环境
     */
    public static final String DEVELOP_MODE = "dev";

    /**
     * windows 系统默认编码
     */
    public static final String SYSTEM_DEFAULT_CHARSET = SystemConfig.getProperty("system.default.charset");

    /**
     * UltraVNC Server 程序映像名称
     */
    public static final String ULTRAVNC_SERVER_IMAGE_NAME = SystemConfig.getProperty("ultravnc.uvncserver.image.name");

    /**
     * 默认 UltraVNC Server 程序映像名称
     */
    public static final String DEFAULT_ULTRAVNC_SERVER_IMAGE_NAME = "winvnc.exe";

    /**
     * UltraVNC Viewer 程序映像名称
     */
    public static final String ULTRAVNC_VIEWER_IMAGE_NAME = SystemConfig.getProperty("ultravnc.uvncviewer.image.name");

    /**
     * 默认 UltraVNC Viewer 程序映像名称
     */
    public static final String DEFAULT_ULTRAVNC_VIEWER_IMAGE_NAME = "vncviewer.exe";

    /**
     * UltraVNC Server 程序路径
     */
    public static final String ULTRAVNC_SERVER_PATH = SystemConfig.getProperty("ultravnc.uvncserver.path");

    /**
     * UltraVNC Viewer 程序路径
     */
    public static final String ULTRAVNC_VIEWER_PATH = SystemConfig.getProperty("ultravnc.uvncviewer.path");

    /**
     * UltraVNC 配置文件 ultravnc.ini 的路径
     */
    public static final String ULTRAVNC_INI_PATH = SystemConfig.getProperty("ultravnc.uvncini.path");

    /**
     * 中继器服务器 IP
     */
    public static final String ULTRAVNC_REPEATER_SERVER_IP = SystemConfig.getProperty("ultravnc.repeater.server.ip");

    /**
     * 中继器 UltraVNC Server 监听端口
     */
    public static final String ULTRAVNC_REPEATER_VNCSERVER_LISTEN_PORT = SystemConfig.getProperty("ultravnc.repeater.uvncserver.listen.port");

    /**
     * 中继器 UltraVNC Viewer 监听端口
     */
    public static final String ULTRAVNC_REPEATER_VNCVIEWER_LISTEN_PORT = SystemConfig.getProperty("ultravnc.repeater.uvncviewer.listen.port");

    /**
     * UltraVNC 修改配置文件 ultravnc.ini未填写参数提示信息
     */
    public static final String MODIFY_ULTRAVNC_INI_NO_PARAMETER_INFO = SystemConfig.getProperty("ultravnc.modify.uvncini.no.parameter.info");

    /**
     * UltraVNC INI Modifier 启动失败提示信息
     */
    public static final String START_ULTRAVNC_INI_MODIFIER_ERROR_INFO = SystemConfig.getProperty("ultravnc.start.modify.uvncini.error.info");

    /**
     * UltraVNC Server 启动失败提示信息
     */
    public static final String START_ULTRAVNC_SERVER_ERROR_INFO = SystemConfig.getProperty("ultravnc.start.uvncserver.error.info");

    /**
     * UltraVNC Server 启动未填写参数提示信息
     */
    public static final String START_ULTRAVNC_SERVER_NO_PARAMETER_INFO = SystemConfig.getProperty("ultravnc.start.uvncserver.no.parameter.info");

    /**
     * UltraVNC Server 停止失败提示信息
     */
    public static final String STOP_ULTRAVNC_SERVER_ERROR_INFO = SystemConfig.getProperty("ultravnc.stop.uvncserver.error.info");

    /**
     * UltraVNC Viewer 启动失败提示信息
     */
    public static final String START_ULTRAVNC_VIEWER_ERROR_INFO = SystemConfig.getProperty("ultravnc.start.uvncviewer.error.info");

    /**
     * UltraVNC Viewer 启动未填写参数提示信息
     */
    public static final String START_ULTRAVNC_VIEWER_NO_PARAMETER_INFO = SystemConfig.getProperty("ultravnc.start.uvncviewer.no.parameter.info");

}