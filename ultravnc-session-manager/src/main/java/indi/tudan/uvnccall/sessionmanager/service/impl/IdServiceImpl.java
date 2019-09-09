package indi.tudan.uvnccall.sessionmanager.service.impl;

import indi.tudan.uvnccall.sessionmanager.common.ConfigConstants;
import indi.tudan.uvnccall.sessionmanager.service.IdService;
import indi.tudan.uvnccall.sessionmanager.utils.IdUtils;
import indi.tudan.uvnccall.sessionmanager.utils.StringUtils;
import org.springframework.stereotype.Service;

/**
 * SessionId 业务接口实现类
 *
 * @author wangtan
 * @date 2019-09-05 12:45:42
 * @since 1.0
 */
@Service
public class IdServiceImpl implements IdService {

    /**
     * 获取一个新的会话 Id
     *
     * @return String
     * @author wangtan
     * @date 2019-09-05 12:47:39
     * @since 1.0
     */
    @Override
    public String getSessionId(String controlledEndIP, String controlledEndUser) {
        String sessionId = IdUtils.getVNCSessionId(true);
        IdUtils.SESSION_MAP.put(sessionId,
                IdUtils.SESSION_MAP.get(sessionId).toBuilder()
                        .controlledEndIP(StringUtils.orElse(controlledEndIP, ConfigConstants.UNKNOWN))
                        .controlledEndUser(StringUtils.orElse(controlledEndUser, ConfigConstants.UNKNOWN))
                        .build());
        return sessionId;
    }

    /**
     * 释放一个会话 Id
     *
     * @param sessionId 会话 Id
     * @author wangtan
     * @date 2019-09-06 11:03:51
     * @since 1.0
     */
    @Override
    public void releaseSessionId(String sessionId) {
        IdUtils.release(sessionId);
    }
}