package cc.iyayu.basis.exception;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 *
 * Description: 用户异常类
 */
public class ISystemUsersException extends IBasicsException {

    public ISystemUsersException(String message) {
        super(message);
    }

    public ISystemUsersException(String message, Throwable cause) {
        super(message, cause);
    }

    public ISystemUsersException(Throwable cause) {
        super(cause);
    }
}
