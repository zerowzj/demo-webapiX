package com.company.project.webapi.web;

import com.company.project.webapi.support.action.ActionExecutor;
import com.company.project.webapi.support.web.Api;
import com.google.common.base.Joiner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Api路由
 *
 * @author wangzhj
 */
@Api("/api")
@RestController
public class ApiRouter {

    private static final String ACTION_PREFIX = "action";

    /**
     * 路由
     *
     * @param module   模块
     * @param action   动作
     * @param request  Http请求
     * @param response Http响应
     * @return Map
     */
    @RequestMapping("/{module}/{action}")
    public Map<String, Object> routeByModule(@PathVariable String module, @PathVariable String action,
                                             HttpServletRequest request, HttpServletResponse response) {
        String actionName = Joiner.on("_").join(ACTION_PREFIX, module, action);
        return ActionExecutor.execute(request, response, actionName);
    }

    /**
     * 路由
     *
     * @param action   动作
     * @param request  Http请求
     * @param response Http响应
     * @return Map
     */
    @RequestMapping("/{action}")
    public Map<String, Object> route(@PathVariable String action,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        String actionName = Joiner.on("_").join(ACTION_PREFIX, action);
        return ActionExecutor.execute(request, response, actionName);
    }
}
