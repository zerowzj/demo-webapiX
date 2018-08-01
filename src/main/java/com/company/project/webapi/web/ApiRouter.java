package com.company.project.webapi.web;

import com.company.project.webapi.support.action.Action;
import com.company.project.webapi.support.web.Api;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
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

    /* Spring容器上下文 */
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
    @RequestMapping(value = {"/{action}", "/{module}/{action}"})
    public Map<String, Object> routeByModule(@PathVariable String module, @PathVariable String action,
                                             HttpServletRequest request, HttpServletResponse response) {
        String[] array = new String[]{};
        if (!Strings.isNullOrEmpty(module)) {
        }
        String actionName = Joiner.on("_").join(ACTION_PREFIX, array);
        if (!CXT.containsBean(actionName)) {
            throw new IllegalStateException("");
        }
        Action actionBean = CXT.getBean(actionName, Action.class);
        return actionBean.doProcess(request, response);
    }
}
