package cc.iyayu.basis.controller;

import cc.iyayu.api.model.Menu;
import cc.iyayu.basis.exception.ISystemUserMenuException;
import cc.iyayu.basis.service.SystemUserMenuService;
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
 * Description: 系统用户菜单
 */
@Controller
@RequestMapping("/backstage/systemUserMenu")
public class SystemUserMenuController {

    @Autowired
    private SystemUserMenuService systemUserMenuService;

    /**
     * 获取系统用户菜单集合
     *
     * @param menu 参数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listMenuAll", method = RequestMethod.GET)
    @RequiresPermissions("systemUserMenu:list")
    public Map<String, Object> listSystemUserPermission(Menu menu) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            map.put("data", systemUserMenuService.listMenuAll(menu));
            map.put("count", systemUserMenuService.getCount(menu));
            map.put("code", "200");
            map.put("msg", "");
            return map;
        } catch (ISystemUserMenuException e) {
            return ErrSystemController.err(map, e);
        }
    }

    /**
     * 根据 menu id 删除菜单
     *
     * @param menuId 参数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteMenuById", method = RequestMethod.POST)
    @RequiresPermissions("systemUserMenu:delete")
    public Map<String, Object> listSystemUserPermission(@RequestParam(value = "menuId", defaultValue = "") String menuId) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            map.put("data", systemUserMenuService.deleteMenuById(menuId));
            map.put("count", "");
            map.put("code", "200");
            map.put("msg", "");
            return map;
        } catch (ISystemUserMenuException e) {
            return ErrSystemController.err(map, e);
        }
    }

    /**
     * 更新菜单信息
     *
     * @param menu 参数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateSystemUserMenuById", method = RequestMethod.POST)
    @RequiresPermissions("systemUserMenu:update")
    public Map<String, Object> updateSystemUserMenuById(@RequestBody Menu menu) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            map.put("data", systemUserMenuService.updateSystemUserMenuById(menu));
            map.put("count", "");
            map.put("code", "200");
            map.put("msg", "");
            return map;
        } catch (ISystemUserMenuException e) {
            return ErrSystemController.err(map, e);
        }
    }

    /**
     * 更新菜单信息
     *
     * @param menu 参数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertSystemUserMenu", method = RequestMethod.POST)
    @RequiresPermissions("systemUserMenu:insert")
    public Map<String, Object> insertSystemUserMenu(@RequestBody Menu menu) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            map.put("data", systemUserMenuService.insertSystemUserMenu(menu));
            map.put("count", "");
            map.put("code", "200");
            map.put("msg", "");
            return map;
        } catch (ISystemUserMenuException e) {
            return ErrSystemController.err(map, e);
        }
    }

    /**
     * 根据id获取菜单信息
     *
     * @param id 参数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMenuById", method = RequestMethod.GET)
    @RequiresPermissions("systemUserMenu:list")
    public Map<String, Object> getMenuById(String id) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            map.put("data", systemUserMenuService.getMenuById(id));
            map.put("count", "");
            map.put("code", "200");
            map.put("msg", "");
            return map;
        } catch (ISystemUserMenuException e) {
            return ErrSystemController.err(map, e);
        }
    }

}
