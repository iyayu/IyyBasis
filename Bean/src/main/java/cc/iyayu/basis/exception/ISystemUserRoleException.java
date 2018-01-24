package cc.iyayu.basis.exception;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 用户角色异常
 */
public class ISystemUserRoleException extends IBasicsException {

    public ISystemUserRoleException(String message) {
        super(message);
    }

    public ISystemUserRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public ISystemUserRoleException(Throwable cause) {
        super(cause);
    }
}
