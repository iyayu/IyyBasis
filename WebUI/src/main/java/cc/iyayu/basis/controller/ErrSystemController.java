package cc.iyayu.basis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author iyayu (613163851@qq.com)
 * @version 1.0
 * <p>
 * Description:
 */
@Controller
@RequestMapping("/err")
public class ErrSystemController {

    @ResponseBody
    @RequestMapping("403")
    public Map<String, Object> noPermission() {
        Map<String, Object> json = new HashMap<String, Object>(0);
        json.put("data", "");
        json.put("code", "403");
        json.put("msg", "<div style=\"text-align: center;\"> <h1 style=\"color: #28B779; font-size: 150px;\">401</h1> <span>您没有对应的权限</span> </div>");
        json.put("count", "");
        return json;
    }

    @ResponseBody
    @RequestMapping("pwdErr")
    public Map<String, Object> pwdErr() {
        Map<String, Object> json = new HashMap<String, Object>(0);
        json.put("data", "");
        json.put("code", "403");
        json.put("msg", "<div style=\"text-align: center;\"> <h1 style=\"color: #28B779; font-size: 150px;\">401</h1> <span>您没有对应的权限</span> </div>");
        json.put("count", "");
        return json;
    }

    /**
     * 异常提示信息
     *
     * @param map
     * @param e
     * @return
     */
    public final static Map<String, Object> err(Map<String, Object> map, Exception e) {
        map.put("data", "");
        map.put("code", "500");
        map.put("msg", e.getMessage());
        map.put("count", "");
        return map;
    }
}
