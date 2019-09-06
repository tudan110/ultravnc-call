package indi.tudan.uvnccall.sessionmanager.service;

import indi.tudan.uvnccall.sessionmanager.Session;

import java.util.Map;

/**
 * SessionId 业务接口
 *
 * @author wangtan
 * @date 2019-09-05 12:44:31
 * @since 1.0
 */
public interface IdService {

    /**
     * 列出所有会话信息
     *
     * @return List<JSONObject>
     * @author wangtan
     * @date 2019-09-05 13:02:59
     * @since 1.0
     */
    Map<String, Session> listSessions();

    /**
     * 获取一个新的会话 Id
     *
     * @return String
     * @author wangtan
     * @date 2019-09-05 12:46:33
     * @since 1.0
     */
    String getSessionId();
}