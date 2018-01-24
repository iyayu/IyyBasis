package cc.iyayu.basis.controller;

import cc.iyayu.basis.service.SystemUserRoleService;
import cc.iyayu.basis.vo.SystemUserRoleVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 系统用户角色 控制器
 */
@Controller
@RequestMapping("/backstage/systemUserRole")
public class SystemUserRoleController {

    @Autowired
    private SystemUserRoleService systemUserRoleService;

    /**
     * 获取系统用户角色集合
     *
     * @param systemUserRoleVO 系统用户角色视图对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listSystemUserRole", method = RequestMethod.GET)
    @RequiresPermissions("systemUserRole:list")
    public Map<String, Object> listSystemUserRole(SystemUserRoleVO systemUserRoleVO) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            map.put("data", systemUserRoleService.listSystemUserRole(systemUserRoleVO));
            map.put("count", systemUserRoleService.getCount(systemUserRoleVO));
            map.put("code", "200");
            map.put("msg", "");
            return map;
        } catch (Exception e) {
            return ErrSystemController.err(map, e);
        }
    }

    /**
     * 通过系统用户角色id获取角色和权限信息
     *
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSystemUserRoleAndPermissionBySystemUserRoleId", method = RequestMethod.GET)
    @RequiresPermissions("systemUserRole:list")
    public Map<String, Object> getSystemUserRoleAndPermissionBySystemUserRoleId(String roleId) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            map.put("data", systemUserRoleService.getSystemUserRoleAndPermissionBySystemUserRoleId(roleId));
            map.put("code", "200");
            map.put("msg", "");
            map.put("count", "");
            return map;
        } catch (Exception e) {
            return ErrSystemController.err(map, e);
        }
    }

    /**
     * 通过id更新系统用户角色信息(包括更新信息和是否禁用)
     *
     * @param systemUserRoleVO 系统用户角色视图对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateSystemUserRoleById", method = RequestMethod.POST)
    @RequiresPermissions("systemUserRole:update")
    public Map<String, Object> updateSystemUserRoleById(@RequestBody SystemUserRoleVO systemUserRoleVO) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            map.put("data", systemUserRoleService.updateSystemUserRoleById(systemUserRoleVO));
            map.put("code", "200");
            map.put("msg", "");
            map.put("count", "");
            return map;
        } catch (Exception e) {
            return ErrSystemController.err(map, e);
        }
    }

    /**
     * 删除系统用户角色
     *
     * @param systemUserRoleVO 系统用户角色视图对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteSystemUserRoleById", method = RequestMethod.POST)
    @RequiresPermissions("systemUserRole:delete")
    public Map<String, Object> deleteSystemUserRoleById(@RequestBody SystemUserRoleVO systemUserRoleVO) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            map.put("data", systemUserRoleService.deleteSystemUserRoleById(systemUserRoleVO.getId()));
            map.put("code", "200");
            map.put("msg", "");
            map.put("count", "");
            return map;
        } catch (Exception e) {
            return ErrSystemController.err(map, e);
        }
    }

    /**
     * 添加系统用户角色
     *
     * @param systemUserRoleVO 系统用户角色视图对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertSystemUserRoleById", method = RequestMethod.POST)
    @RequiresPermissions("systemUserRole:insert")
    public Map<String, Object> insertSystemUserRoleById(@RequestBody SystemUserRoleVO systemUserRoleVO) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            map.put("data", systemUserRoleService.insertSystemUserRoleById(systemUserRoleVO));
            map.put("code", "200");
            map.put("msg", "");
            map.put("count", "");
            return map;
        } catch (Exception e) {
            return ErrSystemController.err(map, e);
        }
    }

}
