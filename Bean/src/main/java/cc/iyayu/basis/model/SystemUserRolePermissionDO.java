package cc.iyayu.basis.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 *
 * Description: system_user_role_permission 表的实体
 */
public class SystemUserRolePermissionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统用户角色权限关联主键
     * system_user_role_permission.id
     */
    private String id;

    /**
     * 系统用户角色主键
     * system_user_role_permission.systemuserroleid
     */
    private String systemUserRoleId;

    /**
     * 系统用户权限主键
     * system_user_role_permission.systemuserpermissionid
     */
    private String systemUserPermissionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystemUserRoleId() {
        return systemUserRoleId;
    }

    public void setSystemUserRoleId(String systemUserRoleId) {
        this.systemUserRoleId = systemUserRoleId;
    }

    public String getSystemUserPermissionId() {
        return systemUserPermissionId;
    }

    public void setSystemUserPermissionId(String systemUserPermissionId) {
        this.systemUserPermissionId = systemUserPermissionId;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
