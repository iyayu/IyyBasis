package cc.iyayu.basis.vo;

import cc.iyayu.basis.model.SystemUserGroupDO;
import com.alibaba.fastjson.JSONObject;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: system_user_group 表的实体 视图对象, 这个对象主要用于将查询出数据返回到视图, 视图传递参数到后台
 */
public class SystemUserGroupVO extends SystemUserGroupDO {
    private Integer page;

    private Integer limit;

    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}