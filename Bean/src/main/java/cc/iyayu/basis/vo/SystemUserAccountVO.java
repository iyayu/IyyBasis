package cc.iyayu.basis.vo;

import cc.iyayu.basis.model.SystemUserAccountDO;
import com.alibaba.fastjson.JSONObject;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: system_user_account 表的实体 视图对象, 这个对象主要用于将查询出数据返回到视图, 视图传递参数到后台
 */
public class SystemUserAccountVO extends SystemUserAccountDO {
    private Integer page;

    private Integer limit;

    private String systemUserRoleIdOrName;

    private String phone;

    private String age;

    private String regTime;

    private String newPassWord;

    public String getNewPassWord() {
        return newPassWord;
    }

    public void setNewPassWord(String newPassWord) {
        this.newPassWord = newPassWord;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSystemUserRoleIdOrName() {
        return systemUserRoleIdOrName;
    }

    public void setSystemUserRoleIdOrName(String systemUserRoleIdOrName) {
        this.systemUserRoleIdOrName = systemUserRoleIdOrName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regtime) {
        this.regTime = regtime;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
