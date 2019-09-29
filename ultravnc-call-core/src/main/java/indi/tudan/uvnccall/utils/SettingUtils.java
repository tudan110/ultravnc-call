package indi.tudan.uvnccall.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.setting.Setting;

/**
 * 配置文件工具类
 *
 * @author wangtan
 * @date 2019-09-29 09:53:26
 * @since 1.0
 */
public class SettingUtils {

    /**
     * Don't let anyone else instantiate this class
     */
    private SettingUtils() {
    }

    /**
     * 读取配置文件
     *
     * @return Setting
     * @date 2019-09-29 10:02:45
     * @since 1.0
     */
    public static Setting init() {
        return new Setting("ultravnc-call.setting");
    }

    /**
     * 读取配置文件
     *
     * @param settingPath 配置文件路径
     * @return Setting
     * @date 2019-09-29 10:14:24
     * @since 1.0
     */
    public static Setting init(String settingPath) {
        return new Setting(settingPath, CharsetUtil.CHARSET_UTF_8, true);
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        Setting setting = SettingUtils.init();
        String province = setting.getStr("province", "info", "000");
        System.out.println(province);
        System.out.println(setting.getByGroup("ultravnc.repeater.server.ip." + province, "repeater"));
    }
}
