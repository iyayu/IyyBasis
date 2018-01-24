package cc.iyayu.basis.vo;

import cc.iyayu.basis.model.SystemUserRoleDO;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: system_user_role 表的实体 视图对象, 这个对象主要用于将查询出数据返回到视图, 视图传递参数到后台
 */
@JsonIgnoreProperties(value = {"handler"})
public class SystemUserRoleVO extends SystemUserRoleDO {
    private Integer page;

    private Integer limit;

    private Integer start;

    private String operation;

    private List<SystemUserPermissionVO> systemUserPermissions;

    public List<SystemUserPermissionVO> getSystemUserPermissions() {
        return systemUserPermissions;
    }

    public void setSystemUserPermissions(List<SystemUserPermissionVO> systemUserPermissions) {
        this.systemUserPermissions = systemUserPermissions;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(String page) {
        try {
            this.page = Integer.parseInt(page);
        } catch (Exception e) {
            this.page = 0;
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        try {
            this.limit = Integer.parseInt(limit);
        } catch (Exception e) {
            this.limit = 0;
        }
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(String start) {
        try {
            this.start = Integer.parseInt(start);
        } catch (Exception e) {
            this.start = 0;
        }
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
