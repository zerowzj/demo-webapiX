package com.company.project.webapi.support.util;

import com.google.common.base.Objects;
import com.google.common.io.Closeables;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Locale;

/**
 * Http Servlet
 *
 * @author wangzhj
 */
public abstract class HttpServlets {

    public static final String HEADER_AJAX = "X-Requested-With";

    public static final String VALUE_AJAX = "XMLHttpRequest";

    public static final String MULTIPART = "multipart/";

    /**
     * 转HttpServletRequest
     *
     * @param request
     * @return HttpServletRequest
     */
    public static HttpServletRequest toHttp(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    /**
     * 转HttpServletResponse
     *
     * @param response
     * @return HttpServletResponse
     */
    public static HttpServletResponse toHttp(ServletResponse response) {
        return (HttpServletResponse) response;
    }

    /**
     * 是否是Ajax请求
     *
     * @param request
     * @return boolean
     */
    public static boolean isAjax(HttpServletRequest request) {
        String value = request.getHeader(HEADER_AJAX);
        if (Objects.equal(VALUE_AJAX, value)) {
            return true;
        }
        return false;
    }

    /**
     * 是否是文件上传
     *
     * @param request
     * @return boolean
     */
    public static boolean isMultipart(HttpServletRequest request) {
        String contentType = request.getContentType();
        if (contentType == null) {
            return false;
        }
        if (contentType.toLowerCase(Locale.ENGLISH).startsWith(MULTIPART)) {
            return true;
        }
        return false;
    }

    /**
     * 获取请求体字符串
     *
     * @param request
     * @return String
     */
    public static String getBodyStr(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            Closeables.closeQuietly(is);
            Closeables.closeQuietly(reader);
        }
        return sb.toString();
    }
}
