package cc.iyayu.basis.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 *
 * Description: system_user_account 表的实体
 */
public class SystemUserAccountDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统用户账号主键
     * system_user_account.id
     */
    private String id;

    /**
     * 系统用户账号代码
     * system_user_account.systemuseraccountcode
     */
    private String systemUserAccountCode;

    /**
     * 系统用户账号名字
     * system_user_account.systemuseraccountname
     */
    private String systemUserAccountName;

    /**
     * 密码
     * system_user_account.password
     */
    private String passWord;

    /**
     * 盐
     * system_user_account.salt
     */
    private String salt;

    /**
     * 是否锁定, 1: 未锁定, 0: 锁定
     * system_user_account.available
     */
    private String available;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystemUserAccountCode() {
        return systemUserAccountCode;
    }

    public void setSystemUserAccountCode(String systemUserAccountCode) {
        this.systemUserAccountCode = systemUserAccountCode;
    }

    public String getSystemUserAccountName() {
        return systemUserAccountName;
    }

    public void setSystemUserAccountName(String systemUserAccountName) {
        this.systemUserAccountName = systemUserAccountName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
