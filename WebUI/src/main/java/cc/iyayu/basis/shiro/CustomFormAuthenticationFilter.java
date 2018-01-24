package cc.iyayu.basis.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 *
 * Description: 授权成功与授权失败后的操作
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    private final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private final String X_REQUESTED_WITH = "X-Requested-With";

    /**
     * 拒绝访问
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return super.onAccessDenied(request, response);
    }

    /**
     * 登录成功
     *
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);

        //将认证成功的对象放入 Session
        httpServletRequest.getSession().setAttribute("userByCodeAndPwd", subject.getPrincipal());

        // 不是ajax请求
        if (!XML_HTTP_REQUEST.equalsIgnoreCase(httpServletRequest.getHeader(X_REQUESTED_WITH))) {
            return super.onLoginSuccess(token, subject, request, response);
        } else {
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.println("{\"data\":\"1\", \"count\":\"0\", \"code\":\"200\", \"msg\":\"\"}");
            out.flush();
            out.close();
        }
        return false;
    }

}
