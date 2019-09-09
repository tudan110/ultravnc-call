package indi.tudan.uvnccall.sessionmanager.utils;

import com.alibaba.fastjson.JSONObject;
import indi.tudan.uvnccall.sessionmanager.model.Session;

import java.util.List;

/**
 * MVC 工具类
 *
 * @author wangtan
 * @date 2019-09-06 10:14:08
 * @since 1.0
 */
public class WebHelper {

    /**
     * 包装返回数据
     *
     * @param data 数据
     * @return JSONObject
     * @author wangtan
     * @date 2019-09-06 10:21:55
     * @since 1.0
     */
    public static JSONObject createResult(List<Session> data) {
        return new JSONObject()
                .fluentPut("data", data)
                .fluentPut("success", true);
    }

    /**
     * 包装返回数据
     *
     * @param data 数据
     * @return JSONObject
     * @author wangtan
     * @date 2019-09-06 10:22:34
     * @since 1.0
     */
    public static JSONObject createResult(List<Session> data, int total) {
        return new JSONObject()
                .fluentPut("data", data)
                .fluentPut("total", total)
                .fluentPut("success", true);
    }
}