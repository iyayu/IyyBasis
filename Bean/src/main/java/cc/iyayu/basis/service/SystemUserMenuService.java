package cc.iyayu.basis.service;

import cc.iyayu.api.model.Menu;
import cc.iyayu.basis.exception.ISystemUserMenuException;

import java.util.List;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 系统用户菜单 操作服务接口
 */
public interface SystemUserMenuService {

    /**
     * 获取所有系统菜单
     *
     * @param menu 参数
     * @return 成功返回集合, 失败返回null或者集合大小为零
     * @throws ISystemUserMenuException
     */
    List<Menu> listMenuAll(Menu menu) throws ISystemUserMenuException;

    /**
     * 获取所有菜单数量
     *
     * @param menu 参数
     * @return 菜单数量
     * @throws ISystemUserMenuException
     */
    Integer getCount(Menu menu) throws ISystemUserMenuException;

    /**
     * 根据 Menu id 来删除菜单
     *
     * @param id 菜单id
     * @return 成功返回1 是吧返回0或抛出异常
     * @throws ISystemUserMenuException
     */
    Integer deleteMenuById(String id) throws ISystemUserMenuException;

    /**
     * 根据id修改菜单信息
     *
     * @param menu 参数
     * @return  成功返回1 失败返回0或null
     * @throws ISystemUserMenuException
     */
    Integer updateSystemUserMenuById(Menu menu) throws ISystemUserMenuException;

    /**
     * 添加菜单信息
     *
     * @param menu 参数
     * @return  成功返回1 失败返回0或null
     * @throws ISystemUserMenuException
     */
    Integer insertSystemUserMenu(Menu menu) throws ISystemUserMenuException;

    /**
     * 根据id获取菜单信息
     *
     * @param id 参数
     * @return 成功返回Menu对象, 失败返回null或属性为null
     * @throws ISystemUserMenuException
     */
    Menu getMenuById(String id) throws ISystemUserMenuException;
}
