package indi.tudan.uvnccall;

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
        // "C:/Program Files/uvnc bvba/UltraVNC/vncviewer.exe" 90699
        VNCUtils.startUltraVNCViewer(args[0], args[1]);

        // "C:/Program Files/uvnc bvba/UltraVNC/vncviewer.exe" 90699 10.20.16.48 5901
//        VNCUtils.startUltraVNCViewer(args[0], args[1], args[2], args[3]);

        /*VNCUtils.startUltraVNCViewer(
                "C:\\Program Files\\uvnc bvba\\UltraVNC\\vncviewer.exe",,
                "90699"
                "10.20.16.48",
                "5901");*/

    }

}