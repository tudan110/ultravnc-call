package indi.tudan.uvnccall;

import indi.tudan.uvnccall.cli.Cli;

/**
 * UltraVNCServer 停止器
 *
 * @author wangtan
 * @date 2019-08-29 14:53:26
 * @since 1.0
 */
public class UltraVNCServerStoper {

    /**
     * 主方法
     */
    public static void main(String[] args) {

        // 停止 UltraVNC Server
        Cli.stopServer(args);
    }

}