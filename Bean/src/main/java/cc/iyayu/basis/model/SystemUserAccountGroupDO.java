package cc.iyayu.basis.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 *
 * Description: system_user_account_group 表的实体
 */
public class SystemUserAccountGroupDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统用户账号组关联主键
     * system_user_account_group.id
     */
    private String id;

    /**
     * 系统用户账号主键
     * system_user_account_group.systemuseraccountid
     */
    private String systemUserAccountId;

    /**
     * 系统用户组主键
     * system_user_account_group.systemusergroupid
     */
    private String systemUserGroupId;

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

    public String getSystemUserGroupId() {
        return systemUserGroupId;
    }

    public void setSystemUserGroupId(String systemUserGroupId) {
        this.systemUserGroupId = systemUserGroupId;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
