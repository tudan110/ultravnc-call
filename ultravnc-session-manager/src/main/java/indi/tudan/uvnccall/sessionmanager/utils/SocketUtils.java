package indi.tudan.uvnccall.sessionmanager.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Socket 工具类
 *
 * @author wangtan
 * @date 2019-09-09 13:56:58
 * @since 1.0
 */
public class SocketUtils {

    /**
     * 绑定 IP 和 端口
     *
     * @param socket 套接字
     * @param host   IP
     * @param port   端口
     * @throws IOException 抛出异常
     * @author wangtan
     * @date 2019-09-09 14:02:46
     * @since 1.0
     */
    private static void bindPort(Socket socket, String host, int port) throws IOException {
        socket.bind(new InetSocketAddress(host, port));
    }

    /**
     * 端口是否可用
     *
     * @param host IP
     * @param port 端口号
     * @return boolean
     * @author wangtan
     * @date 2019-09-09 14:06:17
     * @since 1.0
     */
    public static boolean isPortAvailable(String host, int port) {
        try (Socket socket = new Socket()) {
            bindPort(socket, host, port);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 端口是否可用
     *
     * @param port 端口号
     * @return boolean
     * @author wangtan
     * @date 2019-09-09 14:02:07
     * @since 1.0
     */
    public static boolean isPortAvailable(int port) {
        try (Socket socket = new Socket()) {
            bindPort(socket, "0.0.0.0", port);
            bindPort(socket, InetAddress.getLocalHost().getHostAddress(), port);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isPortAvailable("10.20.16.48", 5901));
        System.out.println(isPortAvailable("10.20.16.48", 5500));
    }
}