package cc.iyayu.basis.mapper;

import cc.iyayu.basis.vo.SystemUserPermissionVO;
import cc.iyayu.basis.vo.SystemUserRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 用户权限操作接口
 * 主要用于操作 'system_user_permission'表 查看和添加对应的信息等操作
 */
public interface SystemUserPermissionMapper {

    /**
     * 获取所有用户权限
     *
     * @param systemUserPermissionVO 中的修改或插入系统用户角色使用
     * @return 成功返回 SystemUserPermissionVO 集合, 失败返回null或集合大小为零
     * @throws Exception
     */
    List<SystemUserPermissionVO> listSystemUserPermission(SystemUserPermissionVO systemUserPermissionVO) throws Exception;

    /**
     * 获取查询出的数量
     * 注: 此方法配合 listSystemUserPermission 使用
     *
     * @param systemUserPermissionVO 参数集合
     * @return 成功返回数量, 失败返回零或null
     * @throws Exception
     */
    Integer getCount(SystemUserPermissionVO systemUserPermissionVO) throws Exception;

    /**
     * 根据参数返回查询的id数量
     * 注: 此方法配合 SystemUserRoleMapper 中的修改或插入系统用户角色使用
     *
     * @param systemUserRoleVO 参数集合
     * @return
     * @throws Exception
     */
    Integer getCountByParameter(SystemUserRoleVO systemUserRoleVO) throws Exception;

}
