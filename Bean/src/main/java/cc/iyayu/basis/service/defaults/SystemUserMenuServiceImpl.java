package cc.iyayu.basis.service.defaults;

import cc.iyayu.api.model.Menu;
import cc.iyayu.basis.exception.ISystemUserMenuException;
import cc.iyayu.basis.exception.ISystemUserRoleException;
import cc.iyayu.basis.mapper.SystemUserMenuMapper;
import cc.iyayu.basis.mapper.SystemUserPermissionMapper;
import cc.iyayu.basis.model.SystemUserMenuDO;
import cc.iyayu.basis.service.SystemUserMenuService;
import cc.iyayu.basis.vo.SystemUserPermissionVO;
import cc.iyayu.basis.vo.SystemUserRoleVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 这是 系统用户菜单 操作服务接口默认实现
 */
public class SystemUserMenuServiceImpl implements SystemUserMenuService {

    private static final int AVAILABLE = 1;
    private static final int UNAVAILABLE = 0;
    private static final String UPDATE = "update";
    private static final String ADD = "add";

    @Autowired(required = false)
    private SystemUserMenuMapper systemUserMenuMapper;

    @Autowired(required = false)
    private SystemUserPermissionMapper systemUserPermissionMapper;

    /**
     * 获取所有系统菜单
     *
     * @param menu 参数
     * @return 成功返回集合, 失败返回null或者集合大小为零
     * @throws ISystemUserMenuException
     */
    @Override
    public List<Menu> listMenuAll(Menu menu) throws ISystemUserMenuException {
        try {
            Integer limit = menu.getLimit();
            if (limit == null || limit <= 0) {
                menu.setLimit("20");
            }

            Integer page = menu.getPage();
            if (page == null || page <= 1) {
                menu.setPage("0");
            } else {
                menu.setPage(String.format("%s", menu.getLimit() * (menu.getPage() - 1)));
            }

            if (menu.getName() == null) {
                menu.setName("");
            }

            return systemUserMenuMapper.listMenuAll(menu);
        } catch (Exception e) {
            throw new ISystemUserMenuException(String.format("获取所有系统菜单 异常:%s", e.getMessage()));
        }
    }

    /**
     * 获取所有菜单数量
     *
     * @param menu 参数
     * @return 菜单数量
     * @throws ISystemUserMenuException
     */
    @Override
    public Integer getCount(Menu menu) throws ISystemUserMenuException {
        try {
            return systemUserMenuMapper.getCount(menu);
        } catch (Exception e) {
            throw new ISystemUserMenuException(String.format("获取所有菜单数量 异常:%s", e.getMessage()));
        }
    }

    /**
     * 根据 Menu id 来删除菜单
     *
     * @param id 菜单id
     * @return 成功返回1 是吧返回0或抛出异常
     * @throws Exception
     */
    @Override
    public Integer deleteMenuById(String id) throws ISystemUserMenuException {
        try {
            if ("".equals(id)){
                throw new ISystemUserMenuException("没有menu id");
            }

            Integer parentId = systemUserMenuMapper.getCountByParentId(id);
            if (parentId > 0) {
                throw new ISystemUserMenuException("此菜单下有子菜单无法删除");
            }
            return systemUserMenuMapper.deleteMenuById(id);
        } catch (Exception e) {
            throw new ISystemUserMenuException(String.format("根据 Menu id 来删除菜单 异常:%s", e.getMessage()));
        }
    }

    /**
     * 根据id修改菜单信息
     *
     * @param menu 参数
     * @return  成功返回1 失败返回0或null
     * @throws ISystemUserMenuException
     */
    @Override
    public Integer updateSystemUserMenuById(Menu menu) throws ISystemUserMenuException {
        try {
            process(menu, UPDATE);
            return systemUserMenuMapper.updateSystemUserMenuById(menu);
        } catch (Exception e) {
            throw new ISystemUserMenuException(String.format("根据id修改菜单信息 异常:%s", e.getMessage()));
        }
    }

    /**
     * 添加菜单信息
     *
     * @param menu 参数
     * @return 成功返回1 失败返回0或null
     * @throws ISystemUserMenuException
     */
    @Override
    public Integer insertSystemUserMenu(Menu menu) throws ISystemUserMenuException {
        try {
            process(menu, ADD);
            return systemUserMenuMapper.insertSystemUserMenu(menu);
        } catch (Exception e) {
            throw new ISystemUserMenuException(String.format("根据id修改菜单信息 异常:%s", e.getMessage()));
        }
    }

    /**
     * 根据id获取菜单信息
     *
     * @param id 参数
     * @return 成功返回Menu对象, 失败返回null或属性为null
     * @throws Exception
     */
    @Override
    public Menu getMenuById(String id) throws ISystemUserMenuException {
        try {
            return systemUserMenuMapper.getMenuById(id);
        } catch (Exception e) {
            throw new ISystemUserMenuException(String.format("根据id获取菜单信息 异常:%s", e.getMessage()));
        }
    }


    /**
     * 统一数据处理
     *
     * @param menu 对象
     * @param action           添加或修改
     * @throws Exception 如果在执行过程中出现异常, 会抛出 Exception 异常.
     */
    private void process(Menu menu, String action) throws Exception {
        if (UPDATE.equals(action)) {
            if (menu.getId() == null
                    || menu.getId().length() != 32) {
                throw new ISystemUserMenuException("ID为null 或 ID长度不为32");
            }
        }

        if (menu.getName() == null
                || "".equals(menu.getName().trim())) {
            throw new ISystemUserRoleException("没有设置系统用户菜单名称");
        }

        if (menu.getAvailable() != null) {
            int i = Integer.parseInt(menu.getAvailable());
            if (i < UNAVAILABLE || i > AVAILABLE) {
                menu.setAvailable(null);
            }
        }

        if (menu.getParentId() != null) {
            Integer countById = systemUserMenuMapper.getCountById(menu.getParentId());
            if (countById == null || countById == 0) {
                throw new ISystemUserRoleException("没有找到插入的父级菜单");
            }
        }

        if (menu.getSystemUserPermissionId() != null) {
            SystemUserRoleVO systemUserRoleVO = new SystemUserRoleVO();
            List<SystemUserPermissionVO> systemUserPermissions = new ArrayList<SystemUserPermissionVO>(0);

            SystemUserPermissionVO systemUserPermissionVO = new SystemUserPermissionVO();
            systemUserPermissionVO.setId(menu.getSystemUserPermissionId());
            systemUserPermissions.add(systemUserPermissionVO);
            systemUserRoleVO.setSystemUserPermissions(systemUserPermissions);

            Integer byParameter = systemUserPermissionMapper.getCountByParameter(systemUserRoleVO);
            if (byParameter == null || byParameter == 0) {
                throw new ISystemUserRoleException("没有找到插入的权限");
            }
        }

    }
}
