package cc.iyayu.basis.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 *
 * Description: system_user_role 表的实体
 */
public class SystemUserRoleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统用户角色
     * system_user_role.id
     */
    private String id;

    /**
     * 角色名
     * system_user_role.name
     */
    private String name;

    /**
     * 描述
     * system_user_role.describe
     */
    private String describe;

    /**
     * 是否锁定, 1: 未锁定, 0: 锁定
     * system_user_role.available
     */
    private String available;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
