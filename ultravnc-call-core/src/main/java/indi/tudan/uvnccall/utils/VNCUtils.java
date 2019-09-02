package indi.tudan.uvnccall.utils;

import indi.tudan.uvnccall.common.ConfigConstants;
import indi.tudan.uvnccall.exception.NoRightAccessException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.text.MessageFormat;

/**
 * 启动器工具类
 *
 * @author wangtan
 * @date 2019-08-29 14:00:35
 * @since 1.0
 */
public class VNCUtils {

    /**
     * Don't let anyone else instantiate this class
     */
    private VNCUtils() {
    }

    /**
     * 获取 UltraVNC Server 程序映像名称
     *
     * @return String
     * @author wangtan
     * @date 2019-08-30 14:18:01
     * @since 1.0
     */
    private static String getUltraVNCServerImageName() {
        return StringUtils.isEmpty(ConfigConstants.ULTRAVNC_SERVER_IMAGE_NAME)
                ? "winvnc.exe" : ConfigConstants.ULTRAVNC_SERVER_IMAGE_NAME;
    }

    /**
     * 获取 UltraVNC Viewer 程序映像名称
     *
     * @return String
     * @author wangtan
     * @date 2019-08-30 14:18:35
     * @since 1.0
     */
    private static String getUltraVNCViewerImageName() {
        return StringUtils.isEmpty(ConfigConstants.ULTRAVNC_VIEWER_IMAGE_NAME)
                ? "vncviewer.exe" : ConfigConstants.ULTRAVNC_VIEWER_IMAGE_NAME;
    }

    /**
     * 获取 UltraVNC Server 程序路径
     *
     * @return String
     * @author wangtan
     * @date 2019-08-30 10:52:56
     * @since 1.0
     */
    private static String getUltraVNCServerPath() {
        return ConfigConstants.ULTRAVNC_SERVER_PATH;
    }

    /**
     * 获取 UltraVNC Viewer 程序路径
     *
     * @return String
     * @author wangtan
     * @date 2019-08-30 10:52:56
     * @since 1.0
     */
    private static String getUltraVNCViewerPath() {
        return ConfigConstants.ULTRAVNC_VIEWER_PATH;
    }

    /**
     * 获取 UltraVNC 配置文件 ultravnc.ini 的路径
     *
     * @return String
     * @author wangtan
     * @date 2019-08-30 14:52:24
     * @since 1.0
     */
    private static String getUltraVNCIniPath() {
        return ConfigConstants.ULTRAVNC_INI_PATH;
    }

    /**
     * 获取中继器服务器 IP
     *
     * @return String
     * @author wangtan
     * @date 2019-08-30 10:52:56
     * @since 1.0
     */
    private static String getRepeaterServerIP() {
        return ConfigConstants.ULTRAVNC_REPEATER_SERVER_IP;
    }

    /**
     * 获取中继器 UltraVNC Server 监听端口
     *
     * @return String
     * @author wangtan
     * @date 2019-08-30 10:56:51
     * @since 1.0
     */
    private static String getRepeaterVNCServerListenPort() {
        return StringUtils.isEmpty(ConfigConstants.ULTRAVNC_REPEATER_VNCSERVER_LISTEN_PORT)
                ? "5500" : ConfigConstants.ULTRAVNC_REPEATER_VNCSERVER_LISTEN_PORT;
    }

    /**
     * 获取中继器 UltraVNC Viewer 监听端口
     *
     * @return String
     * @author wangtan
     * @date 2019-08-30 10:58:08
     * @since 1.0
     */
    private static String getRepeaterVNCViewerListenPort() {
        return StringUtils.isEmpty(ConfigConstants.ULTRAVNC_REPEATER_VNCVIEWER_LISTEN_PORT)
                ? "5901" : ConfigConstants.ULTRAVNC_REPEATER_VNCVIEWER_LISTEN_PORT;
    }

    /**
     * 启动 UltraVNC Server
     *
     * @param puppetIdNumber 被控制端 ID
     * @author wangtan
     * @date 2019-08-30 14:03:07
     * @since 1.0
     */
    public static void startUltraVNCServer(String puppetIdNumber) {
        startUltraVNCServer(puppetIdNumber, getUltraVNCServerPath(), getRepeaterServerIP(), getRepeaterVNCServerListenPort());
    }

    /**
     * 启动 UltraVNC Server
     *
     * @param puppetIdNumber     被控制端 ID
     * @param ultraVNCServerPath 服务端程序路径
     * @author wangtan
     * @date 2019-08-30 11:01:31
     * @since 1.0
     */
    public static void startUltraVNCServer(String puppetIdNumber, String ultraVNCServerPath) {
        startUltraVNCServer(puppetIdNumber, ultraVNCServerPath, getRepeaterServerIP(), getRepeaterVNCServerListenPort());
    }

    /**
     * 启动 UltraVNC Server
     *
     * @param puppetIdNumber     被控制端 ID
     * @param ultraVNCServerPath 服务端程序路径
     * @param repeaterServerIP   中继器服务器 IP
     * @author wangtan
     * @date 2019-08-30 14:04:41
     * @since 1.0
     */
    public static void startUltraVNCServer(String puppetIdNumber, String ultraVNCServerPath, String repeaterServerIP) {
        startUltraVNCServer(puppetIdNumber, ultraVNCServerPath, repeaterServerIP, getRepeaterVNCServerListenPort());
    }

    /**
     * 启动 UltraVNC Server
     *
     * @param puppetIdNumber              被控制端 ID
     * @param ultraVNCServerPath          服务端程序路径
     * @param repeaterServerIP            中继器服务器 IP
     * @param repeaterVNCServerListenPort 中继器 UltraVNC Server 监听端口
     * @author wangtan
     * @date 2019-08-29 14:12:52
     * @since 1.0
     */
    public static void startUltraVNCServer(String puppetIdNumber, String ultraVNCServerPath,
                                           String repeaterServerIP, String repeaterVNCServerListenPort) {

        // 判断 UltraVNC Server 是否已经运行，若已经在运行了，则需要停止
        if (RuntimeUtils.isTaskAlive(getUltraVNCServerImageName())) {
            stopUltraVNCServer(Paths.get(ultraVNCServerPath).getFileName().toString());
        }

        RuntimeUtils.execCatchErrorInfo(MessageFormat.format("{0} -autoreconnect ID:{1} -connect {2}:{3} -run",
                ultraVNCServerPath,
                puppetIdNumber,
                repeaterServerIP,
                repeaterVNCServerListenPort),
                ConfigConstants.START_ULTRAVNC_SERVER_ERROR_INFO
        );
    }

    /**
     * 通过默认映像名（进程名称），停止 UltraVNC Server
     *
     * @author wangtan
     * @date 2019-08-30 14:13:55
     * @since 1.0
     */
    public static void stopUltraVNCServerByDefaultImageName() {
        stopUltraVNCServer(getUltraVNCServerImageName());
    }

    /**
     * 停止 UltraVNC Server
     *
     * @param imageName 映像名（进程名称）
     * @author wangtan
     * @date 2019-08-29 14:55:40
     * @since 1.0
     */
    public static void stopUltraVNCServer(String imageName) {
        RuntimeUtils.execAndPrintResults(MessageFormat.format("cmd /c taskkill /f /im \"{0}\"", imageName),
                ConfigConstants.STOP_ULTRAVNC_SERVER_ERROR_INFO);
    }

    /**
     * 启动 UltraVNC Viewer
     *
     * @param puppetIdNumber 被控制端 ID
     * @author wangtan
     * @date 2019-08-30 13:45:17
     * @since 1.0
     */
    public static void startUltraVNCViewer(String puppetIdNumber) {
        startUltraVNCViewer(puppetIdNumber, getUltraVNCViewerPath(), getRepeaterServerIP(), getRepeaterVNCViewerListenPort());
    }

    /**
     * 启动 UltraVNC Viewer
     *
     * @param puppetIdNumber     被控制端 ID
     * @param ultraVNCViewerPath 查看器程序路径
     * @author wangtan
     * @date 2019-08-30 11:03:17
     * @since 1.0
     */
    public static void startUltraVNCViewer(String puppetIdNumber, String ultraVNCViewerPath) {
        startUltraVNCViewer(puppetIdNumber, ultraVNCViewerPath, getRepeaterServerIP(), getRepeaterVNCViewerListenPort());
    }

    /**
     * 启动 UltraVNC Viewer
     *
     * @param puppetIdNumber     被控制端 ID
     * @param ultraVNCViewerPath 查看器程序路径
     * @param repeaterServerIP   中继器服务器 IP
     * @author wangtan
     * @date 2019-08-30 13:53:17
     * @since 1.0
     */
    public static void startUltraVNCViewer(String puppetIdNumber, String ultraVNCViewerPath, String repeaterServerIP) {
        startUltraVNCViewer(puppetIdNumber, ultraVNCViewerPath, repeaterServerIP, getRepeaterVNCViewerListenPort());
    }

    /**
     * 启动 UltraVNC Viewer
     *
     * @param puppetIdNumber              被控制端 ID
     * @param ultraVNCViewerPath          查看器程序路径
     * @param repeaterServerIP            中继器服务器 IP
     * @param repeaterVNCViewerListenPort 中继器 UltraVNC Viewer 监听端口
     * @author wangtan
     * @date 2019-08-29 14:12:52
     * @since 1.0
     */
    public static void startUltraVNCViewer(String puppetIdNumber, String ultraVNCViewerPath,
                                           String repeaterServerIP, String repeaterVNCViewerListenPort) {
        RuntimeUtils.execCatchErrorInfo(MessageFormat.format("{0} -proxy {1}:{2} ID:{3}",
                ultraVNCViewerPath,
                repeaterServerIP,
                repeaterVNCViewerListenPort,
                puppetIdNumber),
                ConfigConstants.START_ULTRAVNC_VIEWER_ERROR_INFO
        );
    }

    /**
     * 读取 ultravnc.ini 文件内容
     *
     * @param iniFilePath 文件路径
     * @param idNumber    编号
     * @return String 读取的文件内容
     * @author wangtan
     * @date 2019-08-29 11:16:59
     * @since 1.0
     */
    private static String readUltraVNCIni(String iniFilePath, String idNumber) {
        String line;
        StringBuilder buf = new StringBuilder();

        // 根据文件路径创建缓冲输入流
        try (BufferedReader br = new BufferedReader(new FileReader(iniFilePath))) {

            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            while ((line = br.readLine()) != null) {

                // 此处根据实际需要修改中继器反向连接配置行的内容
                // 原始内容格式，如：Service_commandline=-autoreconnect ID:90699 -connect 10.20.16.48:5500
                if (line.startsWith("Service_commandline")) {
                    int startIndex = line.indexOf("ID:") + 3;
                    int endIndex = line.indexOf("-connect") - 1;
                    buf.append(new StringBuilder(line).replace(startIndex, endIndex, idNumber));
                } else {

                    // 如果不用修改, 则按原来的内容回写
                    buf.append(line);
                }
                buf.append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    /**
     * 将内容回写到文件 ultravnc.ini 中
     *
     * @param filePath 文件路径
     * @param content  待写入文件内容
     * @author wangtan
     * @date 2019-08-29 11:22:31
     * @since 1.0
     */
    private static void writeUltraVNCIni(String filePath, String content) {

        // 根据文件路径创建缓冲输出流
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            // 将内容写入文件中
            bw.write(content);
        } catch (Exception e) {
            if (getUltraVNCIniPath().contains("C:/Program Files")) {
                throw new NoRightAccessException("没有权限访问配置文件，请以管理员权限运行程序。");
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 修改 ultravnc.ini 文件内容
     *
     * @param idNumber 编号
     * @author wangtan
     * @date 2019-08-30 14:48:30
     * @since 1.0
     */
    public static void modifyUltraVNCIni(String idNumber) {
        modifyUltraVNCIni(idNumber, getUltraVNCIniPath());
    }

    /**
     * 修改 ultravnc.ini 文件内容
     *
     * @param idNumber 编号
     * @param filePath 文件路径
     * @author wangtan
     * @date 2019-08-29 14:48:08
     * @since 1.0
     */
    public static void modifyUltraVNCIni(String idNumber, String filePath) {

        // 读取并修改文件
        VNCUtils.writeUltraVNCIni(filePath, VNCUtils.readUltraVNCIni(filePath, idNumber));
    }

}