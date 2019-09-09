package indi.tudan.uvnccall.sessionmanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 会话对象
 *
 * @author wangtan
 * @date 2019-09-05 13:16:27
 * @since 1.0
 */
@EqualsAndHashCode
@Data
@Builder(toBuilder = true)
public class Session {

    private String id;

    private String description;

    /**
     * 控制端
     */
    private String controlEndIP;
    private String controlEndUser;

    /**
     * 被控制端
     */
    private String controlledEndIP;
    private String controlledEndUser;

    /**
     * 连接时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime connectTime;
}