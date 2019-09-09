package indi.tudan.uvnccall.sessionmanager.service;

/**
 * SessionId 业务接口
 *
 * @author wangtan
 * @date 2019-09-05 12:44:31
 * @since 1.0
 */
public interface IdService {

    /**
     * 获取一个新的会话 Id
     *
     * @return String
     * @author wangtan
     * @date 2019-09-05 12:46:33
     * @since 1.0
     */
    String getSessionId(String controlledEndIP, String controlledEndUser);

    /**
     * 释放一个会话 Id
     *
     * @param sessionId 会话 Id
     * @author wangtan
     * @date 2019-09-06 11:03:07
     * @since 1.0
     */
    void releaseSessionId(String sessionId);
}