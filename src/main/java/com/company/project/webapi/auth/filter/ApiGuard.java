package com.company.project.webapi.auth.filter;

import com.company.exception.entity.BaseException;
import com.company.exception.entity.param.EmptyValueException;
import com.company.project.webapi.support.Results;
import com.company.project.webapi.support.ext.JsonBodyRequest;
import com.company.util.HttpServlets;
import com.company.util.HttpWrites;
import com.google.common.base.Strings;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Api哨兵
 *
 * @author wangzhj
 */
public class ApiGuard extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiGuard.class);

    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = HttpServlets.toHttp(servletRequest);
        HttpServletResponse response = HttpServlets.toHttp(servletResponse);
        try {
            //Post提交
            String method = request.getMethod();
            if (!"POST".equals(method.toUpperCase())) {
                HttpWrites.writeByJson(response, Results.buildNotOk("9000", "请使用POST提交"));
                return;
            }
            String uri = request.getRequestURI();
            //LOGGER.info("URI[{}]'s content type is [{}]", uri, request.getContentType());
            //包装请求
            request = new JsonBodyRequest(request);
            //
            preHandle(request);
            //继续执行
            filterChain.doFilter(request, response);
        } catch (BaseException ex) {
            ex.printStackTrace();
            String errorCode = ex.getErrorCode();
            String errorDesc = ex.getErrorDesc();
            HttpWrites.writeByJson(response, Results.buildNotOk(errorCode, errorDesc));
        } catch (Exception ex) {
            ex.printStackTrace();
            HttpWrites.writeByJson(response, Results.buildNotOk());
        }
    }

    private void preHandle(HttpServletRequest request) {
        Map<String, Object> params = request.getParameterMap();
        String imei = (String) params.get("imei");
        if (Strings.isNullOrEmpty(imei)) {
            throw new EmptyValueException("imei");
        }
        String osName = (String) params.get("os_name");
        if (Strings.isNullOrEmpty(osName)) {
            throw new EmptyValueException("os_name");
        }
        String osVersion = (String) params.get("os_version");
        if (Strings.isNullOrEmpty(osVersion)) {
            throw new EmptyValueException("os_version");
        }
        String appVersion = (String) params.get("app_version");
        if (Strings.isNullOrEmpty(appVersion)) {
            throw new EmptyValueException("app_version");
        }
        String ip = (String) params.get("ip");
        if (Strings.isNullOrEmpty(ip)) {
            throw new EmptyValueException("ip");
        }
    }
}
