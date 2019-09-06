package indi.tudan.uvnccall.sessionmanager.service.impl;

import indi.tudan.uvnccall.sessionmanager.Session;
import indi.tudan.uvnccall.sessionmanager.service.IdService;
import indi.tudan.uvnccall.sessionmanager.utils.IdUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * SessionId 业务接口实现类
 *
 * @author wangtan
 * @date 2019-09-05 12:45:42
 * @since 1.0
 */
@Service
public class IdserviceImpl implements IdService {

    /**
     * 列出所有会话信息
     *
     * @return List<JSONObject>
     * @author wangtan
     * @date 2019-09-05 13:03:39
     * @since 1.0
     */
    @Override
    public Map<String, Session> listSessions() {
        return IdUtils.SESSION_MAP;
    }

    /**
     * 获取一个新的会话 Id
     *
     * @return String
     * @author wangtan
     * @date 2019-09-05 12:47:39
     * @since 1.0
     */
    @Override
    public String getSessionId() {
        return IdUtils.getVNCSessionId(true);
    }
}