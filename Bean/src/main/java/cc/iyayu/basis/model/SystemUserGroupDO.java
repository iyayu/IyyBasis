package cc.iyayu.basis.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 *
 * Description: system_user_group 表的实体
 */
public class SystemUserGroupDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统用户组主键
     * system_user_group.id
     */
    private String id;

    /**
     * 系统用户组名称
     * system_user_group.name
     */
    private String name;

    /**
     * 系统用户组说明
     * system_user_group.describe
     */
    private String describe;

    /**
     * 系统用户组父结点主键
     * system_user_group.parentid
     */
    private String parentId;

    /**
     * 是否锁定, 1: 未锁定, 0: 锁定
     * system_user_group.available
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
