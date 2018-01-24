package cc.iyayu.basis.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 *
 * Description: system_user_account_role 表的实体
 */
public class SystemUserAccountRoleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统用户账号角色关联表主键
     * system_user_account_role.id
     */
    private String id;

    /**
     * 系统用户账号主键
     * system_user_account_role.systemuseraccountid
     */
    private String systemUserAccountId;

    /**
     * 系统用户角色主键
     * system_user_account_role.systemuserroleid
     */
    private String systemUserRoleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystemUserAccountId() {
        return systemUserAccountId;
    }

    public void setSystemUserAccountId(String systemUserAccountId) {
        this.systemUserAccountId = systemUserAccountId;
    }

    public String getSystemUserRoleId() {
        return systemUserRoleId;
    }

    public void setSystemUserRoleId(String systemUserRoleId) {
        this.systemUserRoleId = systemUserRoleId;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
