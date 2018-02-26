package com.company.project.webapi.web.api;

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

        Map<String, Object> data = Results.data();
        data.put("access_token", "0123456789");
        return data;
    }
}
