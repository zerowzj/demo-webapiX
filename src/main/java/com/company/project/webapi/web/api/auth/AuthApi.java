package com.company.project.webapi.web.api.auth;

import com.company.project.webapi.support.Api;
import com.company.project.webapi.support.action.ActionExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 短信Api
 *
 * @author wangzhj
 */
@Api("/auth")
public class AuthApi {

    @RequestMapping("/get_auth_code")
    @ResponseBody
    public Map<String, Object> get_auth_code(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_get_auth_code.class);
    }

    @RequestMapping("/verify_auth_code")
    @ResponseBody
    public Map<String, Object> verify_auth_code(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_verify_auth_code.class);
    }
}
