package com.company.project.webapi.web.api.login;

import com.company.project.common.UserType;
import com.company.project.common.redis.Redis;
import com.company.project.common.redis.RedisKeys;
import com.company.project.webapi.support.context.RequestContext;
import com.company.project.webapi.support.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 退出登录
 *
 * @author wangzhj
 */
@Component
public class Action_logout extends BaseAction {

    @Autowired
    private Redis redis;

    @Override
    public void checkData(RequestContext cxt, Map<String, Object> params) {

    }

    @Override
    public Map<String, Object> execute(RequestContext cxt, Map<String, Object> params) {
        Long ubId = cxt.getUbId();
        String key = RedisKeys.keyOfUser(UserType.merchant, ubId);
        redis.delete(key);
        return null;
    }
}
