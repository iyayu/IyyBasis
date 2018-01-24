package cc.iyayu.basis.service.defaults;

import cc.iyayu.basis.exception.ISystemUserRoleException;
import cc.iyayu.basis.mapper.SystemUserPermissionMapper;
import cc.iyayu.basis.mapper.SystemUserRoleMapper;
import cc.iyayu.basis.service.SystemUserRoleService;
import cc.iyayu.basis.vo.SystemUserRoleVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 这是 系统用户角色 操作服务接口默认实现
 */
public class SystemUserRoleServiceImpl implements SystemUserRoleService {

    private static final int AVAILABLE = 1;
    private static final int UNAVAILABLE = 0;
    private static final String UPDATE = "update";

    @Autowired(required = false)
    private SystemUserRoleMapper systemUserRoleMapper;

    @Autowired(required = false)
    private SystemUserPermissionMapper systemUserPermissionMapper;

    /**
     * 获取用户角色
     *
     * @param systemUserRoleVO SystemUserRoleVO 对象
     * @return 成功返回限集合, 失败返回null或集合大小为零
     * @throws ISystemUserRoleException 如果在执行过程中出现异常, 会抛出 ISystemUserRoleException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public List<SystemUserRoleVO> listSystemUserRole(SystemUserRoleVO systemUserRoleVO) throws ISystemUserRoleException {
        try {
            Integer limit = systemUserRoleVO.getLimit();
            if (limit == null || limit <= 0) {
                systemUserRoleVO.setLimit("20");
            }

            Integer page = systemUserRoleVO.getPage();
            if (page == null || page <= 1) {
                systemUserRoleVO.setPage("0");
            } else {
                systemUserRoleVO.setPage(String.format("%s", systemUserRoleVO.getLimit() * (systemUserRoleVO.getPage() - 1)));
            }

            if (systemUserRoleVO.getName() == null) {
                systemUserRoleVO.setName("");
            }
            return systemUserRoleMapper.listSystemUserRole(systemUserRoleVO);
        } catch (Exception e) {
            throw new ISystemUserRoleException("获取用户角色异常");
        }
    }

    /**
     * 返回查询数据量
     *
     * @param userRoleVO SystemUserRoleVO 对象
     * @return 返回 listUserRole 方法查询的数据量
     * @throws ISystemUserRoleException 如果在执行过程中出现异常, 会抛出 ISystemUserRoleException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public Integer getCount(SystemUserRoleVO userRoleVO) throws ISystemUserRoleException {
        try {
            return systemUserRoleMapper.getCount(userRoleVO);
        } catch (Exception e) {
            throw new ISystemUserRoleException("获取所有数据量异常");
        }
    }

    /**
     * 通过id获取角色权限信息
     *
     * @param systemRoleId 用户角色ID
     * @return 成功返回对象, 失败返回null或属性为null
     * @throws ISystemUserRoleException 如果在执行过程中出现异常, 会抛出 ISystemUserRoleException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public SystemUserRoleVO getSystemUserRoleAndPermissionBySystemUserRoleId(String systemRoleId) throws ISystemUserRoleException {
        try {
            if (systemRoleId == null || "".equals(systemRoleId)) {
                return null;
            }

            return systemUserRoleMapper.getSystemUserRoleAndPermissionBySystemUserRoleId(systemRoleId);
        } catch (Exception e) {
            throw new ISystemUserRoleException("获取角色权限信息异常");
        }
    }

    /**
     * 通过id更新系统用户角色信息(包括更新信息和是否禁用)
     *
     * @param systemUserRoleVO SystemUserRoleVO 对象
     * @return 成功返回1 失败返回null或零
     * @throws ISystemUserRoleException 如果在执行过程中出现异常, 会抛出 ISystemUserRoleException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public Integer updateSystemUserRoleById(SystemUserRoleVO systemUserRoleVO) throws ISystemUserRoleException {
        try {
            process(systemUserRoleVO, "update");
            return systemUserRoleMapper.updateSystemUserRoleById(systemUserRoleVO);
        } catch (Exception e) {
            throw new ISystemUserRoleException(String.format("通过id更新系统用户角色信息(包括更新信息和是否禁用), 异常信息: %s", e.getMessage()));
        }
    }

    /**
     * 删除系统用户角色
     *
     * @param systemUserRoleId 要删除的系统用户角色id
     * @return 成功返回1 失败返回0或null
     * @throws ISystemUserRoleException
     */
    @Override
    public Integer deleteSystemUserRoleById(String systemUserRoleId) throws ISystemUserRoleException {
        try {
            return systemUserRoleMapper.deleteSystemUserRoleById(systemUserRoleId);
        } catch (Exception e) {
            throw new ISystemUserRoleException(String.format("删除系统用户角色, 异常信息: %s", e.getMessage()));
        }
    }

    /**
     * 添加系统用户角色
     *
     * @param systemUserRoleVO SystemUserRoleVO 对象
     * @return 成功返回1 失败返回null或零
     * @throws ISystemUserRoleException 如果在执行过程中出现异常, 会抛出 ISystemUserRoleException 异常. 其中会包含异常的详细信息.
     */
    @Override
    public Integer insertSystemUserRoleById(SystemUserRoleVO systemUserRoleVO) throws ISystemUserRoleException {
        try {
            process(systemUserRoleVO, "insert");
            return systemUserRoleMapper.insertSystemUserRoleById(systemUserRoleVO);
        } catch (Exception e) {
            throw new ISystemUserRoleException(String.format("添加系统用户角色, 异常信息: %s", e.getMessage()));
        }
    }

    /**
     * 统一数据处理
     *
     * @param systemUserRoleVO SystemUserRoleVO 对象
     * @param action           添加或修改
     * @throws ISystemUserRoleException 如果在执行过程中出现异常, 会抛出 Exception 异常.
     */
    private void process(SystemUserRoleVO systemUserRoleVO, String action) throws Exception {
        if (UPDATE.equals(action)) {
            if (systemUserRoleVO.getId() == null
                    || systemUserRoleVO.getId().length() != 32) {
                throw new ISystemUserRoleException("ID为null 或 ID长度不为32");
            }
        }

        if (systemUserRoleVO.getName() == null
                || "".equals(systemUserRoleVO.getName().trim())) {
            throw new ISystemUserRoleException("没有设置系统用户角色名称");
        }

        if (systemUserRoleVO.getAvailable() != null) {
            int i = Integer.parseInt(systemUserRoleVO.getAvailable());
            if (i < UNAVAILABLE || i > AVAILABLE) {
                systemUserRoleVO.setAvailable(null);
            }
        }

        if (systemUserRoleVO.getSystemUserPermissions() != null) {
            //判断 权限是否存在
            Integer count = systemUserPermissionMapper.getCountByParameter(systemUserRoleVO);
            if (count != null) {
                if (systemUserRoleVO.getSystemUserPermissions().size() != count) {
                    throw new ISystemUserRoleException("查询出的SystemUserPermissionsId数量与传递的集合数量不同");
                }
            } else {
                throw new ISystemUserRoleException("查询SystemUserPermissionsId数量失败");
            }
        }
    }


}
