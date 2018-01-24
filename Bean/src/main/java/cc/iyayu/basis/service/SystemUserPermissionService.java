package cc.iyayu.basis.service;

import cc.iyayu.basis.exception.ISystemUserPermissionException;
import cc.iyayu.basis.vo.SystemUserPermissionVO;

import java.util.List;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 系统用户权限 操作服务接口
 */
public interface SystemUserPermissionService {

    /**
     * 获取所有用户权限
     * @param systemUserPermissionVO 系统用户权限视图对象
     * @return 成功返回用户权限集合, 失败返回null或集合大小为零
     * @throws ISystemUserPermissionException 如果在执行过程中出现异常, 会抛出 ISystemUserPermissionException 异常. 其中会包含异常的详细信息.
     */
    List<SystemUserPermissionVO> listSystemUserPermission(SystemUserPermissionVO systemUserPermissionVO) throws ISystemUserPermissionException;

    /**
     * 获取查询出的数量
     * 注: 此方法配合 listSystemUserPermission 使用
     *
     * @param systemUserPermissionVO 参数集合
     * @return 成功返回数量, 失败返回零或null
     * @throws Exception
     */
    Integer getCount(SystemUserPermissionVO systemUserPermissionVO) throws ISystemUserPermissionException;
}
