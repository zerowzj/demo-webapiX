package com.company.project.webapi.web.api.auth;

import com.company.exception.entity.param.EmptyValueException;
import com.company.project.service.sms.AuthCodeService;
import com.company.project.webapi.support.action.BaseAction;
import com.company.project.webapi.support.context.RequestContext;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 获取短信验证码
 *
 * @author wangzhj
 */
@Component
public class Action_get_auth_code extends BaseAction {

    @Autowired
    private AuthCodeService authCodeService;

    //手机号
    private String cell_phone;

    @Override
    public void checkData(RequestContext cxt, Map<String, Object> params) {
        cell_phone = getParam("cell_phone");
        if (Strings.isNullOrEmpty(cell_phone)) {
            throw new EmptyValueException("cell_phone");
        }
    }

    @Override
    public Map<String, Object> execute(RequestContext cxt, Map<String, Object> params) {
        String authCodeSeq = authCodeService.sendAuthCode(cell_phone);

        Map<String, Object> data = Maps.newHashMap();
        data.put("auth_code_seq", authCodeSeq);
        return data;
    }
}
