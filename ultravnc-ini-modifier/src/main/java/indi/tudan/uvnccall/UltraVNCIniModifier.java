package indi.tudan.uvnccall;

import indi.tudan.uvnccall.utils.VNCUtils;

/**
 * 修改 UltraVNC 配置文件 ultravnc.ini
 *
 * @author wangtan
 * @date 2019-08-29 11:11:29
 * @since 1.0
 */
public class UltraVNCIniModifier {

    /**
     * 主方法
     */
    public static void main(String[] args) {

        // 修改 ultravnc.ini 文件
        // "C:/Users/tudan/Desktop/ultravnc.ini" 90699
        VNCUtils.modifyUltraVNCIni(args[0], args[1]);

//        VNCUtils.modifyUltraVNCIni("C:\\Users\\tudan\\Desktop\\ultravnc.ini", "90699");
    }
}