package com.company.project.webapi.web.action;

import com.company.project.webapi.support.action.BaseAction;
import com.company.project.webapi.support.context.RequestContext;
import com.company.project.webapi.support.web.Param;
import com.company.project.webapi.support.web.Results;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Action_get_token extends BaseAction {

    @Override
    protected Map<String, Object> execute(RequestContext cxt, Param param) {

//        RedisClient.set("mykey", "valuesdfsdflljsafdl");

        Map<String, Object> data = Results.data();
        data.put("ub_id", 666666);
        data.put("access_token", "0123456789");
        data.put("times", "2018");
        return data;
    }
}
