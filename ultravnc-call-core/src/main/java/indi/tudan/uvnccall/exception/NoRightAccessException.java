package indi.tudan.uvnccall.exception;

/**
 * 没有权限访问异常，C盘没有管理员权限访问部分文件
 *
 * @author wangtan
 * @date 2019-08-30 15:05:50
 * @since 1.0
 */
public class NoRightAccessException extends RuntimeException {

    /**
     * 空构造器
     */
    public NoRightAccessException() {
        super();
    }

    /**
     * 构造器
     *
     * @param message 消息
     */
    public NoRightAccessException(String message) {
        super(message);
    }

    /**
     * 构造器
     *
     * @param cause 原因
     */
    public NoRightAccessException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造器
     *
     * @param message 消息
     * @param cause   原因
     */
    public NoRightAccessException(String message, Throwable cause) {
        super(message, cause);
    }

}