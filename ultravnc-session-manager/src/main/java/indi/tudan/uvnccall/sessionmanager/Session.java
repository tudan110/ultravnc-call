package indi.tudan.uvnccall.sessionmanager;

import lombok.Builder;
import lombok.Data;

/**
 * 会话对象
 *
 * @author wangtan
 * @date 2019-09-05 13:16:27
 * @since 1.0
 */
@Data
@Builder
public class Session {

    private String id;

    private String name;

    /**
     * 控制端
     */
    private String controlEndIP;

    private String controlEndUser;

    /**
     * 被控制端
     */
    private String ControlledEndIP;

    private String ControlledEndUser;
}