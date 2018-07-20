package com.company.project.webapi.auth.filter;

import com.company.project.webapi.auth.Uris;
import com.company.project.webapi.common.util.HttpServlets;
import com.company.project.webapi.support.ThreadLocals;
import com.company.project.webapi.support.ext.JsonBodyRequest;
import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 看门狗过滤器
 *
 * @author wangzhj
 */
public class WatchDogFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WatchDogFilter.class);

    private static final String NAME_REQUEST_ID = "Request-Id";

    private static final String REQUEST_ID = "request_id";

    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        LOGGER.info("I am WatchDogFilter!");
        //计时
        Stopwatch stopwatch = Stopwatch.createStarted();
        //
        HttpServletRequest request = HttpServlets.toHttp(servletRequest);
        HttpServletResponse response = HttpServlets.toHttp(servletResponse);
        String uri = request.getRequestURI();
        try {
            System.out.println("+++++++++++++=" + MDC.get(REQUEST_ID));
            //Request Id
            String requestId = request.getHeader(NAME_REQUEST_ID);
            ThreadLocals.setTrackKey(requestId);
            if (Strings.isNullOrEmpty(requestId)) {
                response.sendError(412, "request id为空");
                return;
            }
            MDC.put(REQUEST_ID, requestId);
            //Uri
            if (!Uris.isLegal(uri)) {
                response.sendError(404, "URI非法");
                LOGGER.warn("URI[{}]非法！", uri);
                return;
            }
            //包装，上传文件不包装
            if (!HttpServlets.isMultipart(request)) {
                request = new JsonBodyRequest(request);
            }

            //执行下个Filter
            filterChain.doFilter(request, response);
        } finally {
            LOGGER.info("===> URI[{}] cost [{} ms]", uri, stopwatch.elapsed(TimeUnit.MILLISECONDS));
            MDC.clear();
//            MDC.remove(REQUEST_ID);
            ThreadLocals.removeTrackKey();
        }
    }
}