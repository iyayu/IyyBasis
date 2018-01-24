package cc.iyayu.basis.controller;

import cc.iyayu.api.model.Menu;
import cc.iyayu.basis.exception.ISystemUsersException;
import cc.iyayu.basis.model.SystemUserAccountDO;
import cc.iyayu.basis.service.SystemUserAccountService;
import cc.iyayu.basis.vo.SystemUserAccountVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description: User 控制器
 */
@Controller
@RequestMapping("/backstage/systemUserAccount")
public class SystemUserAccountController {

    @Autowired
    private SystemUserAccountService systemUserAccountService;

    @ResponseBody
    @GetMapping("/login")
    public void login( HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.sendRedirect("/backstage/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public Map<String, Object> login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                     String username, String password) {

        Map<String, Object> map = new HashMap<String, Object>(0);

        if (StringUtils.isBlank(username)) {
            throw new RuntimeException("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            throw new RuntimeException("密码不能为空");
        }


        // 如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String) httpServletRequest.getAttribute("shiroLoginFailure");

        SystemUserAccountDO userByCodeAndPwd = (SystemUserAccountDO) httpServletRequest.getSession().getAttribute("userByCodeAndPwd");
        // 根据shiro返回的异常类路径判断，抛出指定异常信息
        if (exceptionClassName != null) {
            //登录失败
            map.put("data", "0");
            map.put("count", "0");
            map.put("code", "200");
            map.put("msg", "");

        } else if (userByCodeAndPwd != null) {
            try {
                httpServletResponse.sendRedirect("/backstage/index.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 获取菜单
     *
     * @param httpServletRequest
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMenu")
    public Map<String, Object> getMenu(HttpServletRequest httpServletRequest) {
        Map<String, Object> json = new HashMap<String, Object>(0);

        json.put("code", "200");
        json.put("msg", "");

        SystemUserAccountDO systemUserAccountDO;
        List<Menu> menuList;
        try {
            systemUserAccountDO = (SystemUserAccountDO) httpServletRequest.getSession().getAttribute("userByCodeAndPwd");
            if (systemUserAccountDO == null) {
                //恶意请求
                json.put("data", "-1");
                return json;
            }

            menuList = systemUserAccountService.listMenuBySystemUserAccountId(systemUserAccountDO.getId());
        } catch (Exception e) {
            json.put("data", "-2");
            return json;
        }

        json.put("count", menuList.size());
        json.put("data", menuList);
        return json;
    }

    /**
     * 获取用户账号列表
     *
     * @param systemUserAccountVO 这里的参数用作, 查询条件使用
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listSystemUserAccount", method = RequestMethod.GET)
    @RequiresPermissions("systemUserAccount:list")
    public Map<String, Object> listSystemUserAccount(SystemUserAccountVO systemUserAccountVO) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            map.put("data", systemUserAccountService.listSystemUserAccount(systemUserAccountVO));
            map.put("count", systemUserAccountService.getCount(systemUserAccountVO));
            map.put("code", "200");
            map.put("msg", "");
            return map;
        } catch (ISystemUsersException e) {
            return ErrSystemController.err(map, e);
        }
    }

    /**
     * 获取个人账号信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserAccountById", method = RequestMethod.GET)
    public Map<String, Object> getUserAccountById(HttpServletRequest httpServletRequest) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            SystemUserAccountDO systemUserAccountDO = (SystemUserAccountDO) httpServletRequest.getSession().getAttribute("userByCodeAndPwd");
            if (systemUserAccountDO == null || systemUserAccountDO.getId() == null) {
                map.put("data", "");
                map.put("code", "200");
                map.put("msg", "恶意请求");
                map.put("count", "");
                return map;
            }

            SystemUserAccountVO userAccountVO = new SystemUserAccountVO();
            userAccountVO.setRegTime(null);
            userAccountVO.setSystemUserAccountName(null);
            userAccountVO.setSystemUserAccountCode(null);
            userAccountVO.setId(systemUserAccountDO.getId());

            List<SystemUserAccountVO> listUserGroup = systemUserAccountService.listSystemUserAccount(userAccountVO);
            map.put("data", listUserGroup);
            map.put("code", "200");
            map.put("msg", "");
            map.put("count", systemUserAccountService.getCount(userAccountVO));
            return map;
        } catch (Exception e) {
            return ErrSystemController.err(map, e);
        }
    }

    /**
     * 添加系统用户账号
     *
     * @param systemUserAccountVO 系统用户账号视图对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertSystemUserAccount", method = RequestMethod.POST)
    @RequiresPermissions("systemUserAccount:insert")
    public Map<String, Object> insertSystemUserAccount(@RequestBody SystemUserAccountVO systemUserAccountVO) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            map.put("data", systemUserAccountService.insertSystemUserAccount(systemUserAccountVO));
            map.put("count", "0");
            map.put("code", "200");
            map.put("msg", "");
            return map;
        } catch (ISystemUsersException e) {
            return ErrSystemController.err(map, e);
        }
    }

    /**
     * 初始化密码为 iyayu
     *
     * @param systemUserAccountVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/initPwd", method = RequestMethod.POST)
    @RequiresPermissions("systemUserAccount:update")
    public Map<String, Object> initPwd(@RequestBody SystemUserAccountVO systemUserAccountVO) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            map.put("data", systemUserAccountService.initPwd(systemUserAccountVO.getId()));
            map.put("count", "0");
            map.put("code", "200");
            map.put("msg", "");
            return map;
        } catch (Exception e) {
            return ErrSystemController.err(map, e);
        }
    }

    /**
     * 禁用或启用账户
     *
     * @param systemUserAccountVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/disabledOrEnabled", method = RequestMethod.POST)
    @RequiresPermissions("systemUserAccount:update")
    public Map<String, Object> disabledOrEnabled(@RequestBody SystemUserAccountVO systemUserAccountVO) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            map.put("data", systemUserAccountService.disabledOrEnabled(systemUserAccountVO));
            map.put("count", "0");
            map.put("code", "200");
            map.put("msg", "");
            return map;
        } catch (Exception e) {
            return ErrSystemController.err(map, e);
        }
    }

    /**
     * 更新用户信息 (拥有权限的)
     *
     * @param userAccountVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateUserAccount", method = RequestMethod.POST)
    @RequiresPermissions("systemUserAccount:update")
    public Map<String, Object> updateUserAccount(@RequestBody SystemUserAccountVO userAccountVO) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            Integer result = systemUserAccountService.updateUserAccountById(userAccountVO);
            if (result == null || result == 0) {
                map.put("data", "0");
            } else {
                map.put("data", "1");
            }
            map.put("count", "0");
            map.put("code", "200");
            map.put("msg", "");
            return map;
        } catch (Exception e) {
            return ErrSystemController.err(map, e);
        }
    }

    /**
     * 更新用户信息 (拥有权限的)
     *
     * @param userAccountVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateUserAccountRole", method = RequestMethod.POST)
    @RequiresPermissions("systemUserAccount:update")
    public Map<String, Object> updateUserAccountRole(@RequestBody SystemUserAccountVO userAccountVO) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            Integer result = systemUserAccountService.updateUserAccountRole(userAccountVO);
            if (result == null || result == 0) {
                map.put("data", "0");
            } else {
                map.put("data", "1");
            }
            map.put("count", "");
            map.put("code", "200");
            map.put("msg", "");
            return map;
        } catch (Exception e) {
            return ErrSystemController.err(map, e);
        }
    }

    /**
     * 修改密码 (个人)
     *
     * @param systemUserAccountVO
     * @param httpServletRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePassWord", method = RequestMethod.POST)
    public Map<String, Object> updatePassWord(@RequestBody SystemUserAccountVO systemUserAccountVO,
                                              HttpServletRequest httpServletRequest) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        try {
            map.put("count", "0");
            map.put("code", "200");
            map.put("msg", "");

            SystemUserAccountDO systemUserAccountDO =
                    (SystemUserAccountDO) httpServletRequest.getSession().getAttribute("userByCodeAndPwd");
            if (systemUserAccountDO.getId() == null) {
                map.put("data", "0");
                return map;
            }

            map.put("data", systemUserAccountService.updatePassWord(systemUserAccountVO.getPassWord(),
                    systemUserAccountVO.getNewPassWord(), systemUserAccountDO.getId()));
            return map;
        } catch (Exception e) {
            return ErrSystemController.err(map, e);
        }
    }
}
