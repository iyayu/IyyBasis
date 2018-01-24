package cc.iyayu.api.tools;

import cc.iyayu.api.model.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 *
 * Description: 菜单工具类
 */
public class Menus {

    /**
     * 处理菜单
     *
     * @param rootMenu 原始的数据
     * @return 处理完后的菜单
     */
    public List<Menu> handleMenu(List<Menu> rootMenu) {
        // 最后的结果
        List<Menu> menuList = new ArrayList<Menu>();
        // 先找到所有的一级菜单
        for (Menu menu : rootMenu) {
            // 一级菜单parentId 为1
            if ("1".equals(menu.getParentId())) {
                menu.setChildMenus(getChild(menu.getId(), rootMenu));
                menuList.add(menu);
            }
        }
        return menuList;
    }


    /**
     * 递归查找子菜单
     *
     * @param id       当前菜单id
     * @param rootMenu 要查找的列表
     * @return
     */
    private List<Menu> getChild(String id, List<Menu> rootMenu) {
        // 子菜单
        List<Menu> childList = new ArrayList<Menu>();
        for (Menu menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (menu.getParentId().equals(id)) {
                menu.setChildMenus(this.getChild(menu.getId(), rootMenu));
                childList.add(menu);
                /*this.getChild(menu.getId(), rootMenu);*/
            }
        }
        return childList;
    }
}
