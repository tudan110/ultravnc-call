package indi.tudan.uvnccall.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.log.StaticLog;
import cn.hutool.setting.Setting;

/**
 * 配置文件工具类
 *
 * @author wangtan
 * @date 2019-09-29 09:53:26
 * @since 1.0
 */
public class SettingUtils {

    private Setting setting = init();

    /**
     * Don't let anyone else instantiate this class
     */
    private SettingUtils() {
    }

    /**
     * 获取单例对象
     *
     * @return 配置类
     * @date 2019-11-15 10:22:03
     */
    public static SettingUtils getInstance() {
        return SettingUtilsInstance.INSTANCE;
    }

    /**
     * 读取配置文件
     *
     * @return Setting
     * @date 2019-09-29 10:02:45
     * @since 1.0
     */
    public static Setting init() {

        String settingPath = ClassUtils.getCurrentProgramPath() + "/ultravnc-call.setting";

        // 若程序文件不存在，则使用默认配置文件
        if (!FileUtils.isFileExists(settingPath)) {
            StaticLog.info("ultravnc-call.setting is not exists, and then, this program will use default settings.");
            return new Setting("ultravnc-call.setting");
        }

        return new Setting(settingPath);
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

    public Setting getSetting() {
        return setting;
    }

    /**
     * 内部类，实现单例
     *
     * @date 2019-11-15 10:22:08
     */
    private static class SettingUtilsInstance {
        private static final SettingUtils INSTANCE = new SettingUtils();
    }

}
