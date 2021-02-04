package indi.tudan.uvnccall;

import indi.tudan.uvnccall.cli.Cli;

/**
 * UltraVNCServer 启动器
 *
 * @author wangtan
 * @date 2019-08-29 13:54:08
 * @since 1.0
 */
public class UltraVNCServerStarter {

    /**
     * 主方法
     */
    public static void main(String[] args) {

        // 启动 UltraVNC Server
        Cli.startServer(args);

    }

}