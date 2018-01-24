package cc.iyayu.basis.vo;

import cc.iyayu.basis.model.SystemUserPermissionDO;
import com.alibaba.fastjson.JSONObject;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description:  system_user_permission 表的实体 视图对象, 这个对象主要用于将查询出数据返回到视图, 视图传递参数到后台
 */
public class SystemUserPermissionVO extends SystemUserPermissionDO {

    /**
     * 查询类型
     */
    private String queryType;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }
}
