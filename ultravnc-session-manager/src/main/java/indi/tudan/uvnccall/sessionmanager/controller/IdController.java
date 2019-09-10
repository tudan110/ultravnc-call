package indi.tudan.uvnccall.sessionmanager.controller;

import indi.tudan.uvnccall.sessionmanager.service.IdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * SessionId 控制器
 *
 * @author wangtan
 * @date 2019-09-05 12:37:22
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/id")
public class IdController {

    @Autowired
    private IdService idService;

    /**
     * 获取一个新的会话 Id
     * http://localhost:9081/usm/api/id
     *
     * @return String
     * @author wangtan
     * @date 2019-09-05 12:47:10
     * @since 1.0
     */
    @GetMapping
    public String getSessionId(@RequestParam(value = "controlledEndIP", required = false) String controlledEndIP,
                               @RequestParam(value = "controlledEndUser", required = false) String controlledEndUser) {
        log.info("get a new session id.");
        return idService.getSessionId(controlledEndIP, controlledEndUser);
    }

    /**
     * 释放一个会话 Id
     *
     * @param sessionId 会话 Id
     * @author wangtan
     * @date 2019-09-06 11:00:52
     * @since 1.0
     */
    @DeleteMapping
    public void releaseSessionId(@RequestParam(value = "sessionId") String sessionId) {
        log.info("release a session id.");
        idService.releaseSessionId(sessionId);
    }
}