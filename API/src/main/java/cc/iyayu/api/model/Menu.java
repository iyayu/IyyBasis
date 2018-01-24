package cc.iyayu.api.model;

import java.util.List;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * 
 * Description: 菜单类
 */
public class Menu {

    /**
     * 系统用户菜单主键
     * system_user_menu.id
     */
    private String id;

    /**
     * 资源名称
     * system_user_menu.name
     */
    private String name;

    /**
     * url
     * system_user_menu.url
     */
    private String url;

    /**
     * 父结点id
     * system_user_menu.parentid
     */
    private String parentId;

    /**
     * 排序号
     * system_user_menu.queuenumber
     */
    private Long queueNumber;

    /**
     * 图标
     * system_user_menu.icon
     */
    private String icon;

    /**
     * 说明
     * system_user_menu.describe
     */
    private String describe;

    /**
     * 是否锁定, 1: 未锁定, 0: 锁定
     * system_user_menu.available
     */
    private String available;

    /**
     * 子菜单
     */
    private List<Menu> childMenus;

    /**
     * 上级菜单名
     */
    private String parentName;

    private Integer page;

    private Integer limit;

    private String systemUserPermissionId;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Long getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(Long queueNumber) {
        this.queueNumber = queueNumber;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public List<Menu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<Menu> childMenus) {
        this.childMenus = childMenus;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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

    public String getSystemUserPermissionId() {
        return systemUserPermissionId;
    }

    public void setSystemUserPermissionId(String systemUserPermissionId) {
        this.systemUserPermissionId = systemUserPermissionId;
    }
}
