package com.company.project.webapi.web.api.app;

import com.company.project.webapi.web.api.register.Action_register;
import com.company.project.webapi.support.Api;
import com.company.project.webapi.support.action.ActionExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * AppApi
 *
 * @author wangzhj
 */
@Api
public class AppApi {

    @RequestMapping("/111")
    @ResponseBody
    public Map<String, Object> register(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_register.class);
    }
}
