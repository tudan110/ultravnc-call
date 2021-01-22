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

        Cli.startServer(args);

        /*if (!Cli.startServer(args)) {

            // 启动 UltraVNC Server
            switch (args.length) {
                case 1:

                    // 90699
                    VNCUtils.startUltraVNCServer(args[0]);
                    break;
                case 2:

                    // 90699 "C:/Program Files/uvnc bvba/UltraVNC/winvnc.exe"
                    VNCUtils.startUltraVNCServer(args[0], args[1]);
                    break;
                case 3:

                    // 90699 "C:/Program Files/uvnc bvba/UltraVNC/winvnc.exe" 10.20.16.48
                    VNCUtils.startUltraVNCServer(args[0], args[1], args[2]);
                    break;
                case 4:

                    // 90699 "C:/Program Files/uvnc bvba/UltraVNC/winvnc.exe" 10.20.16.48 5500
                    VNCUtils.startUltraVNCServer(args[0], args[1], args[2], args[3]);
                    break;
                default:
                    StaticLog.error(ConfigConstants.START_ULTRAVNC_SERVER_NO_PARAMETER_INFO);
                    break;
            }

        *//*VNCUtils.startUltraVNCServer(
                "C:\\Program Files\\uvnc bvba\\UltraVNC\\winvnc.exe",,
                "90699"
                "10.20.16.48",
                "5500");*//*

        }*/

    }

}