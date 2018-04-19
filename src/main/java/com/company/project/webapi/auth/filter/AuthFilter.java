package com.company.project.webapi.auth.filter;

import com.company.project.webapi.common.util.HttpServlets;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 认证过滤器
 *
 * @author wangzhj
 */
public class AuthFilter extends AdviceFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = HttpServlets.toHttp(servletRequest);
        Map<String, Object> paramMap = request.getParameterMap();
        try {


        } catch (Exception ex) {
            throw ex;
        }
        return true;
    }

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
        LOGGER.info("postHandle......");
    }

    @Override
    public void afterCompletion(ServletRequest servletRequest, ServletResponse servletResponse, Exception ex) throws Exception {

    }
}
