package indi.tudan.uvnccall;

import indi.tudan.uvnccall.common.ConfigConstants;
import indi.tudan.uvnccall.utils.VNCUtils;

/**
 * UltraVNCViewer 启动器
 *
 * @author wangtan
 * @date 2019-08-29 13:54:40
 * @since 1.0
 */
public class UltraVNCViewerStarter {

    /**
     * 主方法
     */
    public static void main(String[] args) {

        // 启动 UltraVNC Viewer
        switch (args.length) {
            case 1:

                // 90699
                VNCUtils.startUltraVNCViewer(args[0]);
                break;
            case 2:

                // 90699 "C:/Program Files/uvnc bvba/UltraVNC/vncviewer.exe"
                VNCUtils.startUltraVNCViewer(args[0], args[1]);
                break;
            case 3:

                // 90699 "C:/Program Files/uvnc bvba/UltraVNC/vncviewer.exe" 10.20.16.48
                VNCUtils.startUltraVNCViewer(args[0], args[1], args[2]);
                break;
            case 4:

                // 90699 "C:/Program Files/uvnc bvba/UltraVNC/vncviewer.exe" 10.20.16.48 5901
                VNCUtils.startUltraVNCViewer(args[0], args[1], args[2], args[3]);
                break;
            default:
                StaticLog.error(ConfigConstants.START_ULTRAVNC_VIEWER_NO_PARAMETER_INFO);
                break;
        }

        /*VNCUtils.startUltraVNCViewer(
                "C:\\Program Files\\uvnc bvba\\UltraVNC\\vncviewer.exe",,
                "90699"
                "10.20.16.48",
                "5901");*/

    }

}