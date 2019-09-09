package indi.tudan.uvnccall.sessionmanager.controller;

import com.alibaba.fastjson.JSONObject;
import indi.tudan.uvnccall.sessionmanager.service.OutlineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 概要信息控制器
 *
 * @author wangtan
 * @date 2019-09-09 14:08:10
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/outline")
public class OutlineController {

    @Autowired
    private OutlineService outlineService;

    /**
     * 获取概要信息
     *
     * @return JSONObject
     * @author wangtan
     * @date 2019-09-09 14:10:23
     * @since 1.0
     */
    @GetMapping
    public JSONObject getOutlineInfo() {
        log.info("get outline info.");
        return outlineService.getOutlineInfo();
    }
}