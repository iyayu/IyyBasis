package cc.iyayu.basis.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 *
 * Description: system_user_info 表的实体
 */
public class SystemUserInfoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统用户信息主键
     * system_user_info.id
     */
    private String id;

    /**
     * 系统用户账号主键
     * system_user_info.systemuseraccountid
     */
    private String systemUserAccountId;

    /**
     * 手机号
     * system_user_info.phone
     */
    private String phone;

    /**
     * 注册时间
     * system_user_info.regtime
     */
    private LocalDateTime regTime;

    /**
     * 注销时间
     * system_user_info.restime
     */
    private LocalDateTime resTime;

    /**
     * 年龄
     * system_user_info.age
     */
    private Integer age;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }

    public LocalDateTime getResTime() {
        return resTime;
    }

    public void setResTime(LocalDateTime resTime) {
        this.resTime = resTime;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
