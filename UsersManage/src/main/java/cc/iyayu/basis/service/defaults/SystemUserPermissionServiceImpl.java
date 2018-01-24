package cc.iyayu.basis.service.defaults;

import cc.iyayu.basis.exception.ISystemUserPermissionException;
import cc.iyayu.basis.mapper.SystemUserPermissionMapper;
import cc.iyayu.basis.service.SystemUserPermissionService;
import cc.iyayu.basis.vo.SystemUserPermissionVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 这是 用户权限 操作服务接口默认实现
 */
public class SystemUserPermissionServiceImpl implements SystemUserPermissionService {

    @Autowired(required = false)
    private SystemUserPermissionMapper systemUserPermissionMapper;

    /**
     * 获取所有用户权限
     *
     * @param systemUserPermissionVO 系统用户权限视图对象
     * @return 成功返回用户权限集合, 失败返回null或集合大小为零
     * @throws ISystemUserPermissionException 如果在执行过程中出现异常, 会抛出 ISystemUserPermissionException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public List<SystemUserPermissionVO> listSystemUserPermission(SystemUserPermissionVO systemUserPermissionVO) throws ISystemUserPermissionException {
        try {
            if (systemUserPermissionVO.getName() == null) {
                systemUserPermissionVO.setName("");
            }
            return systemUserPermissionMapper.listSystemUserPermission(systemUserPermissionVO);
        } catch (Exception e) {
            throw new ISystemUserPermissionException(String.format("查询所有权限, 异常信息: %s", e.getMessage()));
        }
    }

    /**
     * 获取查询出的数量
     * 注: 此方法配合 listSystemUserPermission 使用
     *
     * @param systemUserPermissionVO 参数集合
     * @return 成功返回数量, 失败返回零或null
     * @throws Exception
     */
    @Override
    public Integer getCount(SystemUserPermissionVO systemUserPermissionVO) throws ISystemUserPermissionException {
        try {
            if (systemUserPermissionVO.getName() == null) {
                systemUserPermissionVO.setName("");
            }
            return systemUserPermissionMapper.getCount(systemUserPermissionVO);
        } catch (Exception e) {
            throw new ISystemUserPermissionException(String.format("获取查询出的数量, 异常信息: %s", e.getMessage()));
        }
    }


}
