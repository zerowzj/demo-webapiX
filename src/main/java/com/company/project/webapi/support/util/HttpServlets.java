package com.company.project.webapi.support.util;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class HttpServlets {

    public static HttpServletRequest toHttp(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    public static HttpServletResponse toHttp(ServletResponse response) {
        return (HttpServletResponse) response;
    }

    /**
     * 是否是文件上传
     *
     * @param request
     * @return boolean
     */
    public static boolean isMultipart(HttpServletRequest request) {
        return true;
    }

    /**
     * 获取请求体字符串
     *
     * @param request
     * @return String
     */
    public static String getBodyStr(HttpServletRequest request) {
        return null;
    }
}
