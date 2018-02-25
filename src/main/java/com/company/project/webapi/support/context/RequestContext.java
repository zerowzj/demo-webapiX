package com.company.project.webapi.support.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求上下文
 *
 * @author wangzhj
 */
public class RequestContext {

    /* 请求 */
    private HttpServletRequest request;
    /* 响应 */
    private HttpServletResponse response;
    /* 用户编号 */
    private Long ubId;
    /*  */
    private String accessToken;

    public RequestContext(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Long getUbId() {
        return ubId;
    }

    public void setUbId(Long ubId) {
        this.ubId = ubId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
