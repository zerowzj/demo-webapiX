package com.company.project.webapi.support.action;

import com.company.project.webapi.support.web.Param;
import com.company.project.webapi.support.web.Results;
import com.company.project.webapi.support.context.RequestContext;
import com.company.project.webapi.support.util.JsonUtil;
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
    public final Map<String, Object> doExecute(HttpServletRequest request,
                                               HttpServletResponse response) {
        Map<String, Object> result;
        try {
            //参数
            Map<String, Object> params = request.getParameterMap();
            LOGGER.info("===> i: {}", JsonUtil.toJson(params));
            Param param = new Param(params);
            //上下文
            RequestContext cxt = new RequestContext(request, response);
            Integer ubId = param.get("ub_id");
            String accessToken = param.get("access_token");
            if (ubId != null) {
                cxt.setUbId(new Long(ubId));
                cxt.setAccessToken(accessToken);
            }
            //==>
            checkData(cxt, param);
            //==>
            Map<String, Object> data = execute(cxt, param);
            result = Results.ok(data);
            LOGGER.info("===> o: {}", JsonUtil.toJson(result));
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }

    /**
     * 验证参数
     *
     * @param cxt
     * @param param
     */
    protected void checkData(RequestContext cxt, Param param) {
    }

    /**
     * 执行逻辑
     *
     * @param cxt
     * @param param
     * @return Map
     */
    protected abstract Map<String, Object> execute(RequestContext cxt, Param param);
}
