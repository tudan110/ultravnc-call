package indi.tudan.uvnccall.sessionmanager.service.impl;

import indi.tudan.uvnccall.sessionmanager.model.Session;
import indi.tudan.uvnccall.sessionmanager.service.SessionService;
import indi.tudan.uvnccall.sessionmanager.utils.IdUtils;
import indi.tudan.uvnccall.sessionmanager.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Session 业务接口实现类
 *
 * @author wangtan
 * @date 2019-09-05 12:45:42
 * @since 1.0
 */
@Service
public class SessionServiceImpl implements SessionService {

    /**
     * 列出所有会话信息
     *
     * @return List<JSONObject>
     * @author wangtan
     * @date 2019-09-05 13:03:39
     * @since 1.0
     */
    @Override
    public List<Session> listSessions() {
        return IdUtils.SESSION_MAP.values().stream().collect(Collectors.toList());
    }

    /**
     * 释放所有会话
     *
     * @author wangtan
     * @date 2019-09-06 11:30:25
     * @since 1.0
     */
    @Override
    public void releaseAllSessions() {
        for (Iterator<Map.Entry<String, Session>> it = IdUtils.SESSION_MAP.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Session> item = it.next();
            it.remove();
        }
    }

    /**
     * 更新会话信息
     *
     * @param session 会话信息
     * @author wangtan
     * @date 2019-09-06 15:47:11
     * @since 1.0
     */
    @Override
    public void updateSessionById(Session session) {
        String sessionId = session.getId();
        Session oldSession = IdUtils.SESSION_MAP.get(sessionId);

        IdUtils.SESSION_MAP.put(sessionId,
                oldSession.toBuilder()
                        .description(StringUtils.orElse(session.getDescription(), oldSession.getDescription()))
                        .controlEndIP(StringUtils.orElse(session.getControlEndIP(), oldSession.getControlEndIP()))
                        .controlEndUser(StringUtils.orElse(session.getControlEndUser(), oldSession.getControlEndUser()))
                        .controlledEndIP(StringUtils.orElse(session.getControlledEndIP(), oldSession.getControlledEndIP()))
                        .controlledEndUser(StringUtils.orElse(session.getControlledEndUser(), oldSession.getControlledEndUser()))
                        .build()
        );

        // 判断当前是否连接
        Session currentSession = IdUtils.SESSION_MAP.get(sessionId);
        if (ObjectUtils.isEmpty(currentSession.getConnectTime())
                && StringUtils.isNotEmpty(currentSession.getControlEndIP())
                && StringUtils.isNotEmpty(currentSession.getControlledEndIP())) {
            currentSession.setConnectTime(LocalDateTime.now());
        }

    }
}