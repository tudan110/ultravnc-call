package indi.tudan.uvnccall.sessionmanager.service;

import indi.tudan.uvnccall.sessionmanager.model.Session;

import java.util.List;

/**
 * Session 业务接口
 *
 * @author wangtan
 * @date 2019-09-05 12:44:31
 * @since 1.0
 */
public interface SessionService {

    /**
     * 列出所有会话信息
     *
     * @return List<JSONObject>
     * @author wangtan
     * @date 2019-09-05 13:02:59
     * @since 1.0
     */
    List<Session> listSessions();

    /**
     * 释放所有会话
     *
     * @author wangtan
     * @date 2019-09-06 11:30:16
     * @since 1.0
     */
    void releaseAllSessions();

    /**
     * 更新会话信息
     *
     * @param session 会话信息
     * @author wangtan
     * @date 2019-09-06 15:46:59
     * @since 1.0
     */
    void updateSessionById(Session session);
}