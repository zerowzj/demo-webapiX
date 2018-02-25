package com.company.project.webapi.web.api.login;

import com.company.project.webapi.support.action.ActionExecutor;
import com.company.project.webapi.support.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 登录Api
 *
 * @author wangzhj
 */
@Api
public class LoginApi {

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_login.class);
    }

    @RequestMapping("/login_by_smscode")
    @ResponseBody
    public Map<String, Object> login_by_smcode(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_login_by_smscode.class);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_logout.class);
    }
}
