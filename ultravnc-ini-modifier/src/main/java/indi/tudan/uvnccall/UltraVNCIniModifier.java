package indi.tudan.uvnccall;

import cn.hutool.log.StaticLog;
import indi.tudan.uvnccall.common.ConfigConstants;
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
        if (args.length == 1) {

            // 90699
            VNCUtils.modifyUltraVNCIni(args[0]);
        } else if (args.length == 2) {

            // 90699 "C:/Users/tudan/Desktop/ultravnc.ini"
            VNCUtils.modifyUltraVNCIni(args[0], args[1]);
        } else {
            StaticLog.error(ConfigConstants.MODIFY_ULTRAVNC_INI_NO_PARAMETER_INFO);
        }

//        VNCUtils.modifyUltraVNCIni("C:\\Users\\tudan\\Desktop\\ultravnc.ini", "90699");
    }
}