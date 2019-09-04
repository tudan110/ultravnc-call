package indi.tudan.uvnccall.exception;

/**
 * UltraVNC 程序文件不存在异常
 *
 * @author wangtan
 * @date 2019-09-04 17:14:44
 * @since 1.0
 */
public class UltraVNCException extends RuntimeException {

    /**
     * 空构造器
     */
    public UltraVNCException() {
        super();
    }

    /**
     * 构造器
     *
     * @param message 消息
     */
    public UltraVNCException(String message) {
        super(message);
    }

    /**
     * 构造器
     *
     * @param cause 原因
     */
    public UltraVNCException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造器
     *
     * @param message 消息
     * @param cause   原因
     */
    public UltraVNCException(String message, Throwable cause) {
        super(message, cause);
    }

}