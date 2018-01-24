package cc.iyayu.basis.exception;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 用户权限异常类
 */
public class ISystemUserPermissionException extends IBasicsException {
    public ISystemUserPermissionException(String message) {
        super(message);
    }

    public ISystemUserPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ISystemUserPermissionException(Throwable cause) {
        super(cause);
    }
}
