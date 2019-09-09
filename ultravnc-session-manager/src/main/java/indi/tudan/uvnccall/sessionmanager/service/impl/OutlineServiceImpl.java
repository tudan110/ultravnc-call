package indi.tudan.uvnccall.sessionmanager.service.impl;

import com.alibaba.fastjson.JSONObject;
import indi.tudan.uvnccall.sessionmanager.common.ConfigConstants;
import indi.tudan.uvnccall.sessionmanager.service.OutlineService;
import indi.tudan.uvnccall.sessionmanager.utils.SocketUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OutlineServiceImpl implements OutlineService {

    @Value("${repeater.host}")
    private String repeaterIP;

    @Value("${repeater.serverPort}")
    private int serverPort;

    @Value("${repeater.viewerPort}")
    private int viewerPort;

    /**
     * 获取概要信息
     *
     * @author wangtan
     * @date 2019-09-09 14:13:41
     * @since 1.0
     */
    @Override
    public JSONObject getOutlineInfo() {
        return new JSONObject().fluentPut("name", ConfigConstants.DEFAULT_REPEATER_NAME)
                .fluentPut("state", isUltraVNCRepeaterNormal())
                .fluentPut("ip", repeaterIP)
                .fluentPut("serverPort", serverPort)
                .fluentPut("viewerPort", viewerPort);
    }

    /**
     * 中继服务器是否正常
     *
     * @return boolean
     * @author wangtan
     * @date 2019-09-09 14:14:58
     * @since 1.0
     */
    private boolean isUltraVNCRepeaterNormal() {
        return (!SocketUtils.isPortAvailable(repeaterIP, serverPort))
                && (!SocketUtils.isPortAvailable(repeaterIP, viewerPort));
    }
}
