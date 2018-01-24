package cc.iyayu.basis.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 *
 * Description: system_user_permission 表的实体
 */
public class SystemUserPermissionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统用户权限主键
     * system_user_permission.id
     */
    private String id;

    /**
     * 资源名称
     * system_user_permission.name
     */
    private String name;

    /**
     * 权限代码字符串
     * system_user_permission.percode
     */
    private String perCode;

    /**
     * 说明
     * system_user_permission.describe
     */
    private String describe;

    /**
     * 是否锁定, 1: 未锁定, 0: 锁定
     * system_user_permission.available
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

    public String getPerCode() {
        return perCode;
    }

    public void setPerCode(String perCode) {
        this.perCode = perCode;
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
