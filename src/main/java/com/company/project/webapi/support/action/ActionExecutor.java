package com.company.project.webapi.support.action;

import com.company.project.webapi.support.context.SpringContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Action执行器
 *
 * @author wangzhj
 */
public class ActionExecutor {

    /**
     * 执行Action
     *
     * @param request  Http请求
     * @param response Http响应
     * @param clazz    执行类
     * @return Map
     */
    public static Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response,
                                              Class<? extends Action> clazz) {
        if (!SpringContext.containsBean(clazz)) {
            throw new IllegalStateException("Bean不存在");
        }
        Action action = SpringContext.getBean(clazz);
        return action.doExecute(request, response);
    }
}
