package com.company.project.webapi.web;

import com.company.project.webapi.support.action.Action;
import com.company.project.webapi.support.action.ActionExecutors;
import com.company.project.webapi.support.web.Api;
import com.google.common.base.Joiner;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Api路由
 *
 * @author wangzhj
 */
@Api
public class ApiRouter implements ApplicationContextAware {

    private static final String ACTION_PREFIX = "action";

    private static ApplicationContext CXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CXT = applicationContext;
    }

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
        Action actionBean = CXT.getBean(actionName, Action.class);
        return actionBean.doProcess(request, response);
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
        return ActionExecutors.execute(request, response, actionName);
    }
}
