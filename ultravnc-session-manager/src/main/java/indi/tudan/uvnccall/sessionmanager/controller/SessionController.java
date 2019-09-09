package indi.tudan.uvnccall.sessionmanager.controller;

import com.alibaba.fastjson.JSONObject;
import indi.tudan.uvnccall.sessionmanager.model.Session;
import indi.tudan.uvnccall.sessionmanager.service.SessionService;
import indi.tudan.uvnccall.sessionmanager.utils.WebHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Session 会话控制器
 *
 * @author wangtan
 * @date 2019-09-05 12:37:22
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    /**
     * 列出所有会话信息
     *
     * @return List<JSONObject>
     * @author wangtan
     * @date 2019-09-05 13:01:50
     * @since 1.0
     */
    @GetMapping
    public JSONObject listSession(@RequestParam(value = "pageNum") int pageNum,
                                  @RequestParam(value = "pageSize") int pageSize) {
        log.info("list all sessions info.");
        List<Session> data = sessionService.listSessions();
        int fromIndex = (pageNum - 1) * pageSize;
        int toIndex = Math.min(data.size(), (pageNum * pageSize));
        return WebHelper.createResult(
                data.subList(fromIndex, toIndex),
                data.size()
        );
    }

    /**
     * 更新会话信息
     *
     * @param id                会话 id
     * @param description       描述
     * @param controlEndIP      控制端IP
     * @param controlEndUser    控制端用户
     * @param controlledEndIP   被控制端IP
     * @param controlledEndUser 被控制端用户
     * @date 2019-09-06 15:44:31
     * @author wangtan
     * @since 1.0
     */
    @PutMapping
    public void updateSession(@RequestParam(value = "id") String id,
                              @RequestParam(value = "description", required = false) String description,
                              @RequestParam(value = "controlEndIP", required = false) String controlEndIP,
                              @RequestParam(value = "controlEndUser", required = false) String controlEndUser,
                              @RequestParam(value = "controlledEndIP", required = false) String controlledEndIP,
                              @RequestParam(value = "controlledEndUser", required = false) String controlledEndUser) {
        log.info("update session info by id.");
        sessionService.updateSessionById(Session.builder()
                .id(id)
                .description(description)
                .controlEndIP(controlEndIP)
                .controlEndUser(controlEndUser)
                .controlledEndIP(controlledEndIP)
                .controlledEndUser(controlledEndUser)
                .build());
    }

    /**
     * 释放所有会话
     *
     * @author wangtan
     * @date 2019-09-06 11:29:20
     * @since 1.0
     */
    @DeleteMapping
    public void releaseAllSessions() {
        log.info("release all sessions.");
        sessionService.releaseAllSessions();
    }
}