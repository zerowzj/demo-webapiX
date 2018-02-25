package com.company.project.webapi.web.api.auth;

import com.company.exception.entity.business.WrongException;
import com.company.exception.entity.param.EmptyValueException;
import com.company.project.service.sms.AuthCodeService;
import com.company.project.webapi.support.action.BaseAction;
import com.company.project.webapi.support.context.RequestContext;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 验证短信验证码
 *
 * @author wangzhj
 */
@Component
public class Action_verify_auth_code extends BaseAction {

    @Autowired
    private AuthCodeService authCodeService;

    //验证码
    private String auth_code;
    //验证码Seq
    private String auth_code_seq;

    @Override
    public void checkData(RequestContext requestContext, Map<String, Object> params) {
        auth_code = getParam("auth_code");
        if (Strings.isNullOrEmpty(auth_code)) {
            throw new EmptyValueException("auth_code");
        }
        auth_code_seq = getParam("auth_code_seq");
        if (Strings.isNullOrEmpty(auth_code_seq)) {
            throw new EmptyValueException("auth_code_seq");
        }
    }

    @Override
    public Map<String, Object> execute(RequestContext requestContext, Map<String, Object> params) {
        boolean isOk = authCodeService.verifyAuthCode(auth_code, auth_code_seq);
        if (!isOk) {
            throw new WrongException("验证码");
        }
        return null;
    }
}
