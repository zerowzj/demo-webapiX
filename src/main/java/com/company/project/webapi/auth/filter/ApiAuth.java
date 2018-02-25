package com.company.project.webapi.auth.filter;

import com.company.exception.entity.BaseException;
import com.company.exception.entity.auth.TokenExpiredOrErrorException;
import com.company.exception.entity.param.EmptyValueException;
import com.company.project.common.UserType;
import com.company.project.common.redis.Redis;
import com.company.project.common.redis.RedisKeys;
import com.company.project.webapi.support.Results;
import com.company.util.HttpServlets;
import com.company.util.HttpWrites;
import com.google.common.base.Strings;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Api认证
 *
 * @author wangzhj
 */
public class ApiAuth extends AdviceFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiAuth.class);

    @Autowired
    private Redis redis;

    @Override
    protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = HttpServlets.toHttp(servletRequest);
        Map<String, Object> params = request.getParameterMap();
        try {
            //验证ub id
            Integer ubId = (Integer) params.get("ub_id");
            if (ubId == null) {
                throw new EmptyValueException("ub_id");
            }
            //验证access token
            String accessToken = (String) params.get("access_token");
            if (Strings.isNullOrEmpty(accessToken)) {
                throw new EmptyValueException("access_token");
            }
            String key = RedisKeys.keyOfUser(UserType.merchant, new Long(ubId));
            boolean isExist = redis.exist(key);
            if (!isExist) {
                throw new TokenExpiredOrErrorException();
            }
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
    public void afterCompletion(ServletRequest servletRequest, ServletResponse servletResponse,
                                Exception ex) throws Exception {
        if (ex != null) {
            HttpServletResponse response = HttpServlets.toHttp(servletResponse);
            String errorCode;
            String errorDesc;
            if (ex instanceof BaseException) {
                BaseException bex = (BaseException) ex;
                errorCode = bex.getErrorCode();
                errorDesc = bex.getErrorDesc();
            } else {
                errorCode = "9999";
                errorDesc = "系统异常";
            }
            HttpWrites.writeByJson(response, Results.buildNotOk(errorCode, errorDesc));
        }
    }
}
