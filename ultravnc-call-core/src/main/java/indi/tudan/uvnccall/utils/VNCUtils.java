package indi.tudan.uvnccall.utils;

import indi.tudan.uvnccall.common.ConfigConstants;
import indi.tudan.uvnccall.common.SystemConfig;

import java.io.*;
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
     * 获取 UltraVNC Server 程序映像名称
     *
     * @return String
     * @author wangtan
     * @date 2019-08-30 14:18:01
     * @since 1.0
     */
    private static String getUltraVNCServerImageName() {
        String imageName = SystemConfig.getProperty("ultravnc.uvncserver.image.name");
        return StringUtils.isEmpty(imageName) ? "winvnc.exe" : imageName;
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
        String imageName = SystemConfig.getProperty("ultravnc.uvncviewer.image.name");
        return StringUtils.isEmpty(imageName) ? "vncviewer.exe" : imageName;
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
        return SystemConfig.getProperty("ultravnc.uvncserver.path");
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
        return SystemConfig.getProperty("ultravnc.uvncviewer.path");
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
        return SystemConfig.getProperty("ultravnc.repeater.server.ip");
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
        String repeaterVNCServerListenPort = SystemConfig.getProperty("ultravnc.repeater.uvncserver.listen.port");
        return StringUtils.isEmpty(repeaterVNCServerListenPort) ? "5500" : repeaterVNCServerListenPort;
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
        String repeaterVNCViewerListenPort = SystemConfig.getProperty("ultravnc.repeater.uvncviewer.listen.port");
        return StringUtils.isEmpty(repeaterVNCViewerListenPort) ? "5901" : repeaterVNCViewerListenPort;
    }

    /**
     * 启动本地程序
     *
     * @param command 启动命令字符串
     * @author wangtan
     * @date 2019-08-29 14:01:07
     * @since 1.0
     */
    public static void start(String command) throws IOException {

        // 启动本地程序
        Runtime.getRuntime().exec(command);
    }

    /**
     * 启动 UltraVNC Server
     *
     * @param puppetID 被控制端 ID
     * @author wangtan
     * @date 2019-08-30 14:03:07
     * @since 1.0
     */
    public static void startUltraVNCServer(String puppetID) {
        startUltraVNCServer(puppetID, getUltraVNCServerPath(), getRepeaterServerIP(), getRepeaterVNCServerListenPort());
    }

    /**
     * 启动 UltraVNC Server
     *
     * @param puppetID           被控制端 ID
     * @param ultraVNCServerPath 服务端程序路径
     * @author wangtan
     * @date 2019-08-30 11:01:31
     * @since 1.0
     */
    public static void startUltraVNCServer(String puppetID, String ultraVNCServerPath) {
        startUltraVNCServer(puppetID, ultraVNCServerPath, getRepeaterServerIP(), getRepeaterVNCServerListenPort());
    }

    /**
     * 启动 UltraVNC Server
     *
     * @param puppetID           被控制端 ID
     * @param ultraVNCServerPath 服务端程序路径
     * @param repeaterServerIP   中继器服务器 IP
     * @author wangtan
     * @date 2019-08-30 14:04:41
     * @since 1.0
     */
    public static void startUltraVNCServer(String puppetID, String ultraVNCServerPath, String repeaterServerIP) {
        startUltraVNCServer(puppetID, ultraVNCServerPath, repeaterServerIP, getRepeaterVNCServerListenPort());
    }

    /**
     * 启动 UltraVNC Server
     *
     * @param puppetID                    被控制端 ID
     * @param ultraVNCServerPath          服务端程序路径
     * @param repeaterServerIP            中继器服务器 IP
     * @param repeaterVNCServerListenPort 中继器 UltraVNC Server 监听端口
     * @author wangtan
     * @date 2019-08-29 14:12:52
     * @since 1.0
     */
    public static void startUltraVNCServer(String puppetID, String ultraVNCServerPath,
                                           String repeaterServerIP, String repeaterVNCServerListenPort) {
        try {
            start(MessageFormat.format("{0} -autoreconnect ID:{1} -connect {2}:{3} -run",
                    ultraVNCServerPath,
                    puppetID,
                    repeaterServerIP,
                    repeaterVNCServerListenPort));
        } catch (IOException e) {
            System.out.println(ConfigConstants.START_ULTRAVNC_SERVER_ERROR_INFO);
            e.printStackTrace();
        }
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
        try {
            String cmd = MessageFormat.format("cmd /c " + "taskkill /f /im \"{0}\"", imageName);

            // 获取运行时系统
            Runtime rt = Runtime.getRuntime();

            // 执行命令
            Process proc = rt.exec(cmd);

            // 获取输入流
            InputStream stderr = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(stderr, "GBK");
            BufferedReader br = new BufferedReader(isr);
            String line;

            // 打印出命令执行的结果
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(ConfigConstants.STOP_ULTRAVNC_SERVER_ERROR_INFO);
            e.printStackTrace();
        }
    }

    /**
     * 启动 UltraVNC Viewer
     *
     * @param puppetID 被控制端 ID
     * @author wangtan
     * @date 2019-08-30 13:45:17
     * @since 1.0
     */
    public static void startUltraVNCViewer(String puppetID) {
        startUltraVNCViewer(puppetID, getUltraVNCViewerPath(), getRepeaterServerIP(), getRepeaterVNCViewerListenPort());
    }

    /**
     * 启动 UltraVNC Viewer
     *
     * @param puppetID           被控制端 ID
     * @param ultraVNCViewerPath 查看器程序路径
     * @author wangtan
     * @date 2019-08-30 11:03:17
     * @since 1.0
     */
    public static void startUltraVNCViewer(String puppetID, String ultraVNCViewerPath) {
        startUltraVNCViewer(puppetID, ultraVNCViewerPath, getRepeaterServerIP(), getRepeaterVNCViewerListenPort());
    }

    /**
     * 启动 UltraVNC Viewer
     *
     * @param puppetID           被控制端 ID
     * @param ultraVNCViewerPath 查看器程序路径
     * @param repeaterServerIP   中继器服务器 IP
     * @author wangtan
     * @date 2019-08-30 13:53:17
     * @since 1.0
     */
    public static void startUltraVNCViewer(String puppetID, String ultraVNCViewerPath, String repeaterServerIP) {
        startUltraVNCViewer(puppetID, ultraVNCViewerPath, repeaterServerIP, getRepeaterVNCViewerListenPort());
    }

    /**
     * 启动 UltraVNC Viewer
     *
     * @param puppetID                    被控制端 ID
     * @param ultraVNCViewerPath          查看器程序路径
     * @param repeaterServerIP            中继器服务器 IP
     * @param repeaterVNCViewerListenPort 中继器 UltraVNC Viewer 监听端口
     * @author wangtan
     * @date 2019-08-29 14:12:52
     * @since 1.0
     */
    public static void startUltraVNCViewer(String puppetID, String ultraVNCViewerPath,
                                           String repeaterServerIP, String repeaterVNCViewerListenPort) {
        try {
            start(MessageFormat.format("{0} -proxy {1}:{2} ID:{3}",
                    ultraVNCViewerPath,
                    repeaterServerIP,
                    repeaterVNCViewerListenPort,
                    puppetID));
        } catch (IOException e) {
            System.out.println(ConfigConstants.START_ULTRAVNC_VIEWER_ERROR_INFO);
            e.printStackTrace();
        }
    }

    /**
     * 读取 ultravnc.ini 文件内容
     *
     * @param iniFilePath 文件路径
     * @param workNum     工号
     * @return String 读取的文件内容
     * @author wangtan
     * @date 2019-08-29 11:16:59
     * @since 1.0
     */
    private static String readUltraVNCIni(String iniFilePath, String workNum) {
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
                    buf.append(new StringBuilder(line).replace(startIndex, endIndex, workNum));
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
            e.printStackTrace();
        }
    }

    /**
     * 修改 ultravnc.ini 文件内容
     *
     * @param filePath 文件路径
     * @param workNum  工号
     * @author wangtan
     * @date 2019-08-29 14:48:08
     * @since 1.0
     */
    public static void modifyUltraVNCIni(String filePath, String workNum) {

        // 读取并修改文件
        VNCUtils.writeUltraVNCIni(filePath, VNCUtils.readUltraVNCIni(filePath, workNum));
    }

}