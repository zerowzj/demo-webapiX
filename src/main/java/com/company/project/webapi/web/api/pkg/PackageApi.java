package com.company.project.webapi.web.api.pkg;

import com.company.project.webapi.support.Api;
import com.company.project.webapi.support.action.ActionExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 包裹Api
 *
 * @author wangzhj
 */
@Api("/package")
public class    PackageApi {

    @RequestMapping("/check_in")
    @ResponseBody
    public Map<String, Object> check_in(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_check_in.class);
    }

    @RequestMapping("/get_getting_list")
    @ResponseBody
    public Map<String, Object> get_getting_list(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_get_getting_list.class);
    }

    @RequestMapping("/get_got_list")
    @ResponseBody
    public Map<String, Object> get_got_list(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_get_got_list.class);
    }
}
