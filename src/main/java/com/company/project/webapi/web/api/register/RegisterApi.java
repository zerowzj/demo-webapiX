package com.company.project.webapi.web.api.register;

import com.company.project.webapi.support.Api;
import com.company.project.webapi.support.action.ActionExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 注册Api
 *
 * @author wangzhj
 */
@Api
public class RegisterApi {

    @RequestMapping("/register")
    @ResponseBody
    public Map<String, Object> register(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_register.class);
    }

    @RequestMapping("/reset_pwd")
    @ResponseBody
    public Map<String, Object> reset_pwd(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_reset_pwd.class);
    }
}
