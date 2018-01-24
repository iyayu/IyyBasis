package cc.iyayu.basis.mapper;

import cc.iyayu.basis.vo.SystemUserRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 用户角色操作接口
 * 主要用于操作 'system_user_role'表 查看和添加对应的信息等操作
 */
public interface SystemUserRoleMapper {

    /**
     * 获取用户角色
     *
     * @param systemUserRoleVO 用户角色视图对象
     * @return 返回用户角色集合
     * @throws Exception
     */
    List<SystemUserRoleVO> listSystemUserRole(SystemUserRoleVO systemUserRoleVO) throws Exception;

    /**
     * 获取所有数据量
     *
     * @param userRoleVO 用户角色视图对象
     * @return 成功返回数据量
     * @throws Exception
     */
    Integer getCount(SystemUserRoleVO userRoleVO) throws Exception;

    /**
     * 获取总数据量(根据id)
     *
     * @param id 主键
     * @return 成功返回数据量
     * @throws Exception
     */
    Integer getCountById(@Param(value = "id") String id) throws Exception;

    /**
     * 通过id获取角色权限信息
     *
     * @param systemUserRoleId 系统角色id
     * @return 成功返回对象, 失败返回null或属性为null
     * @throws Exception
     */
    SystemUserRoleVO getSystemUserRoleAndPermissionBySystemUserRoleId(@Param(value = "systemUserRoleId") String systemUserRoleId) throws Exception;

    /**
     * 通过id更新系统用户角色信息(包括更新信息和是否禁用)
     *
     * @param systemUserRoleVO 用户角色视图对象
     * @return 成功返回1 失败返回0或null
     * @throws Exception
     */
    Integer updateSystemUserRoleById(SystemUserRoleVO systemUserRoleVO) throws Exception;

    /**
     * 删除系统用户角色
     *
     * @param systemUserRoleId 系统用户角色id
     * @return 成功返回1 失败返回0或null
     * @throws Exception
     */
    Integer deleteSystemUserRoleById(@Param(value = "systemUserRoleId") String systemUserRoleId) throws Exception;

    /**
     * 添加系统用户角色
     *
     * @param systemUserRoleVO 系统角色id
     * @return 成功返回1 失败返回0或null
     * @throws Exception
     */
    Integer insertSystemUserRoleById(SystemUserRoleVO systemUserRoleVO) throws Exception;
}
