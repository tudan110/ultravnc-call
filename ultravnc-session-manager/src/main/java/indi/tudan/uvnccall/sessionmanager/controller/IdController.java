package indi.tudan.uvnccall.sessionmanager.controller;

import indi.tudan.uvnccall.sessionmanager.Session;
import indi.tudan.uvnccall.sessionmanager.service.IdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * SessionId 控制器
 *
 * @author wangtan
 * @date 2019-09-05 12:37:22
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/session")
public class IdController {

    @Autowired
    private IdService idService;

    /**
     * 列出所有会话信息
     *
     * @return List<JSONObject>
     * @author wangtan
     * @date 2019-09-05 13:01:50
     * @since 1.0
     */
    @GetMapping
    public Map<String, Session> listSession() {
        log.info("list all session info.");
        return idService.listSessions();
    }

    /**
     * 获取一个新的会话 Id
     *
     * @return String
     * @author wangtan
     * @date 2019-09-05 12:47:10
     * @since 1.0
     */
    @GetMapping("id")
    public String getSessionId() {
        log.info("get a new session id.");
        return idService.getSessionId();
    }
}