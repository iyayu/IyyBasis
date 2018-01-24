package cc.iyayu.basis.controller;

import cc.iyayu.basis.exception.ISystemUserPermissionException;
import cc.iyayu.basis.service.SystemUserPermissionService;
import cc.iyayu.basis.vo.SystemUserPermissionVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: 系统用户权限
 */
@Controller
@RequestMapping("/backstage/systemUserPermission")
public class SystemUserPermissionController {

    @Autowired
    private SystemUserPermissionService systemUserPermissionService;

    /**
     * 获取系统用户权限集合
     * @param systemUserPermissionVO 参数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listSystemUserPermission", method = RequestMethod.GET)
    @RequiresPermissions("systemUserPermission:list")
    public Map<String, Object> listSystemUserPermission(SystemUserPermissionVO systemUserPermissionVO) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            map.put("data", systemUserPermissionService.listSystemUserPermission(systemUserPermissionVO));
            map.put("count", systemUserPermissionService.getCount(systemUserPermissionVO));
            map.put("code", "200");
            map.put("msg", "");
            return map;
        } catch (ISystemUserPermissionException e) {
            return ErrSystemController.err(map, e);
        }
    }


}
