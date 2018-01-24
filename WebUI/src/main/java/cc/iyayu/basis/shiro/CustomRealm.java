package cc.iyayu.basis.shiro;

import cc.iyayu.basis.model.SystemUserAccountDO;
import cc.iyayu.basis.service.SystemUserAccountService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 登录与权限获取
 */
public class CustomRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRealm.class);

    @Autowired
    private SystemUserAccountService systemUserAccountService;


    /**
     * 对登录用户进行授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        try {
            // 从 principals获取主身份信息
            // 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
            SystemUserAccountDO systemUserAccountDO = (SystemUserAccountDO) principals.getPrimaryPrincipal();

            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

            if (systemUserAccountDO != null || systemUserAccountDO.getId() != null) {
                // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
                List<String> stringList = systemUserAccountService.listPermissionBySystemUserAccountId(systemUserAccountDO.getId());
                simpleAuthorizationInfo.addStringPermissions(stringList);
            }

            return simpleAuthorizationInfo;
        } catch (Exception e) {
            LOGGER.error(String.format("用户授权出错, 异常信息:%s", e));
            return null;
        }
    }

    /**
     * 用户登录进行认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            String userName = (String) token.getPrincipal();
            String passWord = String.valueOf((char[]) token.getCredentials());
            if (userName == null || passWord == null) {
                return null;
            }

            LOGGER.debug(String.format("用户[%s]认证", userName));

            SystemUserAccountDO systemUserAccountDO = systemUserAccountService.login(userName, passWord);
            if (systemUserAccountDO == null) {
                return null;
            }

            return new SimpleAuthenticationInfo(systemUserAccountDO, passWord, userName);
        } catch (Exception e) {
            LOGGER.error(String.format("用户认证出错, 异常信息:%s", e));
            return null;
        }
    }


}