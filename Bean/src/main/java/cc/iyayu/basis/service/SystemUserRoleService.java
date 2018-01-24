package cc.iyayu.basis.service;

import cc.iyayu.basis.exception.ISystemUserRoleException;
import cc.iyayu.basis.vo.SystemUserRoleVO;

import java.util.List;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 用户角色 操作服务接口
 */
public interface SystemUserRoleService {

    /**
     * 获取用户角色
     *
     * @param systemUserRoleVO SystemUserRoleVO 对象
     * @return 成功返回限集合, 失败返回null或集合大小为零
     * @throws ISystemUserRoleException 如果在执行过程中出现异常, 会抛出 ISystemUserRoleException 异常. 其中会包含异常的详细信息.
     */
    List<SystemUserRoleVO> listSystemUserRole(SystemUserRoleVO systemUserRoleVO) throws ISystemUserRoleException;

    /**
     * 返回查询数据量
     *
     * @param userRoleVO SystemUserRoleVO 对象
     * @return 返回 listUserRole 方法查询的数据量
     * @throws ISystemUserRoleException 如果在执行过程中出现异常, 会抛出 ISystemUserRoleException 异常. 其中会包含异常的详细信息.
     */
    Integer getCount(SystemUserRoleVO userRoleVO) throws ISystemUserRoleException;

    /**
     * 通过id获取角色权限信息
     *
     * @param systemRoleId 系统角色ID
     * @return 成功返回对象, 失败返回null或属性为null
     * @throws ISystemUserRoleException 如果在执行过程中出现异常, 会抛出 ISystemUserRoleException 异常. 其中会包含异常的详细信息.
     */
    SystemUserRoleVO getSystemUserRoleAndPermissionBySystemUserRoleId(String systemRoleId) throws ISystemUserRoleException;

    /**
     * 通过id更新系统用户角色信息(包括更新信息和是否禁用)
     *
     * @param systemUserRoleVO SystemUserRoleVO 对象
     * @return 成功返回1 失败返回null或零
     * @throws ISystemUserRoleException 如果在执行过程中出现异常, 会抛出 ISystemUserRoleException 异常. 其中会包含异常的详细信息.
     */
    Integer updateSystemUserRoleById(SystemUserRoleVO systemUserRoleVO) throws ISystemUserRoleException;

    /**
     * 删除系统用户角色
     *
     * @param systemUserRoleId 要删除的系统用户角色id
     * @return 成功返回1 失败返回0或null
     * @throws ISystemUserRoleException
     */
    Integer deleteSystemUserRoleById(String systemUserRoleId) throws ISystemUserRoleException;

    /**
     * 添加系统用户角色
     *
     * @param systemUserRoleVO SystemUserRoleVO 对象
     * @return 成功返回1 失败返回null或零
     * @throws ISystemUserRoleException 如果在执行过程中出现异常, 会抛出 ISystemUserRoleException 异常. 其中会包含异常的详细信息.
     */
    Integer insertSystemUserRoleById(SystemUserRoleVO systemUserRoleVO) throws ISystemUserRoleException;
}
