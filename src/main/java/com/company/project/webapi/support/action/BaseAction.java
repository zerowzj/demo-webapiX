package com.company.project.webapi.support.action;

import com.company.project.webapi.common.util.JsonUtil;
import com.company.project.webapi.support.context.RequestContext;
import com.company.project.webapi.support.Param;
import com.company.project.webapi.support.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandles;
import java.util.Map;

/**
 * 基础Action
 *
 * @author wangzhj
 */
public abstract class BaseAction implements Action {

    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public final Map<String, Object> doProcess(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result;
        try {
            //参数
            Map<String, Object> paramMap = request.getParameterMap();
            LOGGER.info("===> i: {}", JsonUtil.toJson(paramMap));
            Param param = new Param(paramMap);
            //上下文
            RequestContext cxt = new RequestContext(request, response);
            Integer ubId = param.get("ub_id");
            String accessToken = param.get("access_token");
            if (ubId != null) {
                cxt.setUbId(new Long(ubId));
                cxt.setAccessToken(accessToken);
            }
            //==>
            checkInput(cxt, param);
            //==>
            Map<String, Object> data = processBusiness(cxt, param);
            result = Results.ok(data);
            LOGGER.info("===> o: {}", JsonUtil.toJson(result));
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }

    /**
     * 验证输入
     *
     * @param cxt   请求上下文
     * @param param 参数
     */
    protected void checkInput(RequestContext cxt, Param param) {
    }

    /**
     * 处理业务
     *
     * @param cxt   请求上下文
     * @param param 参数
     * @return Map
     */
    protected abstract Map<String, Object> processBusiness(RequestContext cxt, Param param);
}
