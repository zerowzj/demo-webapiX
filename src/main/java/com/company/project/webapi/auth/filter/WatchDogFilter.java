package com.company.project.webapi.auth.filter;

import com.company.project.webapi.auth.Uris;
import com.company.project.webapi.support.util.HttpServlets;
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
        //计时
        Stopwatch stopwatch = Stopwatch.createStarted();
        //
        HttpServletRequest request = HttpServlets.toHttp(servletRequest);
        HttpServletResponse response = HttpServlets.toHttp(servletResponse);
        String uri = request.getRequestURI();
        try {
            //Request Id
            String requestId = request.getHeader(NAME_REQUEST_ID);
            if (Strings.isNullOrEmpty(requestId)) {
                requestId = "1234567890";
                //LOGGER.warn("URI[{}]使用自动生成的request_id[{}]！", uri, requestId);
            }
            MDC.put(REQUEST_ID, requestId);
            //Uri
            if (!Uris.isLegal(uri)) {
                response.sendError(404);
                LOGGER.warn("URI[{}]非法！", uri);
                return;
            }
            //执行下个Filter
            filterChain.doFilter(request, response);
        } finally {
            LOGGER.info("===> URI[{}] cost [{} ms]", uri, stopwatch.elapsed(TimeUnit.MILLISECONDS));
            MDC.clear();
        }
    }
}