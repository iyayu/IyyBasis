package cc.iyayu.basis.exception;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 *
 * Description: iyayu 项目基础异常类
 */
public class IBasicsException extends Exception {

    public IBasicsException(String message) {
        super(message);
    }

    public IBasicsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IBasicsException(Throwable cause) {
        super(cause);
    }
}
