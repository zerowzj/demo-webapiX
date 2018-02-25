package com.company.project.webapi.auth.support;

import javax.servlet.http.HttpServletRequest;

/**
 * HttpServletRequest Holder
 *
 * @author wangzhj
 */
public abstract class Request {

    private static ThreadLocal<HttpServletRequest> LOCAL = new ThreadLocal();

    private Request() {
    }

    public static HttpServletRequest get() {
        return LOCAL.get();
    }

    public static void set(HttpServletRequest request) {
        LOCAL.set(request);
    }

    public static void remove() {
        LOCAL.remove();
    }
}
