package indi.tudan.uvnccall.utils;

import indi.tudan.uvnccall.common.ConfigConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * windows 系统工具类
 *
 * @author wangtan
 * @date 2019-09-01 01:21:05
 * @since 1.0
 */
public class RuntimeUtils {

    /**
     * Don't let anyone else instantiate this class
     */
    private RuntimeUtils() {
    }

    /**
     * 运行特定命令
     *
     * @param command 命令脚本
     * @author wangtan
     * @date 2019-09-01 05:30:11
     * @since 1.0
     */
    public static Process exec(String command) throws IOException {

        // 启动本地程序
        return Runtime.getRuntime().exec(command);
    }

    /**
     * 运行特定命令
     *
     * @param command   命令脚本
     * @param errorInfo 错误信息
     * @author wangtan
     * @date 2019-09-01 06:13:55
     * @since 1.0
     */
    public static void execCatchErrorInfo(String command, String errorInfo) {

        try {

            // 运行命令脚本
            exec(command);

        } catch (Exception e) {
            if (StringUtils.isNotEmpty(errorInfo)) {
                System.out.println(errorInfo);
            }
            e.printStackTrace();
        }
    }

    /**
     * 运行特定命令
     *
     * @param command   命令字符串
     * @param errorInfo 错误信息
     * @author wangtan
     * @date 2019-09-01 05:28:31
     * @since 1.0
     */
    public static void execAndPrintResults(String command, String errorInfo) {

        BufferedReader br = null;

        try {

            // 启动本地程序
            Process proc = exec(command);

            br = new BufferedReader(
                    new InputStreamReader(proc.getInputStream(), ConfigConstants.SYSTEM_DEFAULT_CHARSET));

            String line;

            // 打印出命令执行的结果
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            if (StringUtils.isNotEmpty(errorInfo)) {
                System.out.println(errorInfo);
            }
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 列出 windows 当前运行的进程
     *
     * @author wangtan
     * @date 2019-08-31 23:39:19
     * @since 1.0
     */
    public static void listTask() {
        execAndPrintResults("tasklist", "列出 windows 当前运行的进程失败。");
    }

    /**
     * 进程是否正在运行
     *
     * @param processImageName 进程映像名称
     * @return boolean
     * @author wangtan
     * @date 2019-08-31 23:53:09
     * @since 1.0
     */
    public static boolean isTaskAlive(String processImageName) {
        BufferedReader br = null;
        try {
            Process proc = Runtime.getRuntime().exec(
                    MessageFormat.format("tasklist /fi \"imagename eq {0}\"", processImageName)
            );
            br = new BufferedReader(new InputStreamReader(proc.getInputStream(), ConfigConstants.SYSTEM_DEFAULT_CHARSET));
            String line;
            while ((line = br.readLine()) != null) {

                // 判断指定的进程是否在运行
                if (line.contains(processImageName)) {
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 进程是否正在运行，使用 Stream anyMatch，练手的 😊，效率不高
     *
     * @param processImageName 进程映像名称
     * @return boolean
     * @author wangtan
     * @date 2019-09-01 00:34:07
     * @since 1.0
     */
    public static boolean isTaskAliveByStreamAnyMatch(String processImageName) {
        List<String> processInfoList = new ArrayList<>();
        BufferedReader br = null;
        try {
            Process proc = Runtime.getRuntime().exec(
                    MessageFormat.format("tasklist /fi \"imagename eq {0}\"", processImageName)
            );
            br = new BufferedReader(new InputStreamReader(proc.getInputStream(), ConfigConstants.SYSTEM_DEFAULT_CHARSET));

            String line;
            while ((line = br.readLine()) != null) {
                processInfoList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return processInfoList.stream().anyMatch(processInfo -> processInfo.contains(processImageName));
    }

}