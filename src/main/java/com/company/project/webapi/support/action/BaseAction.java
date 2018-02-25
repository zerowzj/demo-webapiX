package com.company.project.webapi.support.action;

import com.company.project.webapi.support.Results;
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

    /* 参数 */
    private Map<String, Object> params;

    @Override
    public final Map<String, Object> doExecute(HttpServletRequest request,
                                               HttpServletResponse response) {
        //设置参数
        this.params = request.getParameterMap();
        LOGGER.info("===> i: {}", JsonUtil.toJson(params));
        //执行操作
        Map<String, Object> result;
        try {
            //生成上下文
            RequestContext cxt = new RequestContext(request, response);
            Integer ubId = getParam("ub_id");
            String accessToken = getParam("access_token");
            if (ubId != null) {
                cxt.setUbId(new Long(ubId));
                cxt.setAccessToken(accessToken);
            }

            checkData(cxt, params);

            Map<String, Object> data = execute(cxt, params);
            result = Results.buildOk(data);
            LOGGER.info("===> o: {}", JsonUtil.toJson(result));
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }

    /**
     * 取得参数
     *
     * @param paramName
     * @return T
     */
    protected final <T> T getParam(String paramName) {
        return (T) params.get(paramName);
    }

    /**
     * 验证参数
     *
     * @param cxt
     * @param params
     */
    public abstract void checkData(RequestContext cxt, Map<String, Object> params);

    /**
     * 执行逻辑
     *
     * @param cxt
     * @param params
     * @return Map
     */
    public abstract Map<String, Object> execute(RequestContext cxt, Map<String, Object> params);
}
