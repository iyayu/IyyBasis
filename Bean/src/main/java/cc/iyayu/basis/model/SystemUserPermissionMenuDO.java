package cc.iyayu.basis.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 *
 * Description: system_user_permission_menu 表的实体
 */
public class SystemUserPermissionMenuDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统用户权限与菜单关联主键
     * system_user_permission_menu.id
     */
    private String id;

    /**
     * 系统用户权限主键
     * system_user_permission_menu.systemuserpermissionid
     */
    private String systemUserPermissionId;

    /**
     * 系统用户菜单主键
     * system_user_permission_menu.systemusermenuid
     */
    private String systemUserMenuId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystemUserPermissionId() {
        return systemUserPermissionId;
    }

    public void setSystemUserPermissionId(String systemUserPermissionId) {
        this.systemUserPermissionId = systemUserPermissionId;
    }

    public String getSystemUserMenuId() {
        return systemUserMenuId;
    }

    public void setSystemUserMenuId(String systemUserMenuId) {
        this.systemUserMenuId = systemUserMenuId;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
