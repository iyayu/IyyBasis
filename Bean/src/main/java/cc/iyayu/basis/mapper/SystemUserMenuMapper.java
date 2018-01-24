package cc.iyayu.basis.mapper;

import cc.iyayu.api.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 用户信息操作接口
 * 主要用于操作 'system_user_menu'表 查看和添加对应的信息等操作
 */
public interface SystemUserMenuMapper {

    /**
     * 通过用户账号id查询出用户菜单id
     *
     * @param systemUserAccountId 系统用户账号主键
     * @return 成功返回集合, 失败返回null或者集合大小为零
     * @throws Exception
     */
    List<String> listMenuIdBySystemUserAccountId(@Param(value = "systemUserAccountId") String systemUserAccountId) throws Exception;

    /**
     * 通过系统用户菜单id获取菜单集合
     *
     * @param systemUserMenuIds 系统用户账号主键
     * @return 成功返回集合, 失败返回null或者集合大小为零
     * @throws Exception
     */
    List<Menu> listMenuBySystemUserMenuId(@Param(value = "systemUserMenuIds") List<String> systemUserMenuIds) throws Exception;

    /**
     * 获取所有系统菜单
     *
     * @param menu 参数
     * @return 成功返回集合, 失败返回null或者集合大小为零
     * @throws Exception
     */
    List<Menu> listMenuAll(Menu menu) throws Exception;

    /**
     * 根据id获取菜单信息
     *
     * @param id 参数
     * @return 成功返回Menu对象, 失败返回null或属性为null
     * @throws Exception
     */
    Menu getMenuById(@Param(value = "id") String id) throws Exception;

    /**
     * 获取所有菜单数量
     *
     * @param menu 参数
     * @return 菜单数量
     * @throws Exception
     */
    Integer getCount(Menu menu) throws Exception;

    /**
     * 根据 Menu id 来删除菜单
     *
     * @param id 菜单id
     * @return 成功返回1 是吧返回0或抛出异常
     * @throws Exception
     */
    Integer deleteMenuById(@Param(value = "id") String id) throws Exception;

    /**
     * 通过 父节id 来查询子菜单数量
     * @param id 父节点id
     * @return  成功返回子节点数量, 没有返回0或null
     * @throws Exception
     */
    Integer getCountByParentId(@Param(value = "id") String id) throws Exception;

    /**
     * 根据id修改菜单信息
     *
     * @param menu 参数
     * @return  成功返回1 失败返回0或null
     * @throws Exception
     */
    Integer updateSystemUserMenuById(Menu menu) throws Exception;

    /**
     * 添加菜单信息
     *
     * @param menu 参数
     * @return  成功返回1 失败返回0或null
     * @throws Exception
     */
    Integer insertSystemUserMenu(Menu menu) throws Exception;

    /**
     * 通过 id 查询是否存在菜单
     * @param id id
     * @return  成功返回子节点数量, 没有返回0或null
     * @throws Exception
     */
    Integer getCountById(@Param(value = "id") String id) throws Exception;
}
