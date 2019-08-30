package indi.tudan.uvnccall.utils;

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
     * 启动 UltraVNC Viewer
     *
     * @param ultraVNCViewerPath       查看器程序路径
     * @param repeaterIP               中继器 IP
     * @param repeaterListenViewerPort 中继器 Viewer 监听端口
     * @param puppetID                 被控制端 ID
     * @author wangtan
     * @date 2019-08-29 14:12:52
     * @since 1.0
     */
    public static void startUltraVNCViewer(String ultraVNCViewerPath, String repeaterIP,
                                           String repeaterListenViewerPort, String puppetID) {
        try {
            start(MessageFormat.format("{0} -proxy {1}:{2} ID:{3}",
                    ultraVNCViewerPath,
                    repeaterIP,
                    repeaterListenViewerPort,
                    puppetID));
        } catch (IOException e) {
            System.out.println("UltraVNC Viewer open failed.");
            e.printStackTrace();
        }
    }

    /**
     * 启动 UltraVNC Server
     *
     * @param ultraVNCServerPath       服务端程序路径
     * @param repeaterIP               中继器 IP
     * @param repeaterListenServerPort 中继器 Server 监听端口
     * @param puppetID                 被控制端 ID
     * @author wangtan
     * @date 2019-08-29 14:12:52
     * @since 1.0
     */
    public static void startUltraVNCServer(String ultraVNCServerPath, String repeaterIP,
                                           String repeaterListenServerPort, String puppetID) {
        try {
            start(MessageFormat.format("{0} -autoreconnect ID:{1} -connect {2}:{3} -run",
                    ultraVNCServerPath,
                    puppetID,
                    repeaterIP,
                    repeaterListenServerPort));
        } catch (IOException e) {
            System.out.println("UltraVNC Server open failed.");
            e.printStackTrace();
        }
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
            System.out.println("UltraVNC Server stop failed.");
            e.printStackTrace();
        }
    }

    /**
     * 读取 ultravnc.ini 文件内容
     *
     * @param filePath 文件路径
     * @param workNum  工号
     * @return String 读取的文件内容
     * @author wangtan
     * @date 2019-08-29 11:16:59
     * @since 1.0
     */
    public static String readUltraVNCIni(String filePath, String workNum) {
        String line;
        StringBuilder buf = new StringBuilder();

        // 根据文件路径创建缓冲输入流
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            while ((line = br.readLine()) != null) {

                // 此处根据实际需要修改某些行的内容
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
    public static void writeUltraVNCIni(String filePath, String content) {

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