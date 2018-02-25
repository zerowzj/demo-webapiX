package com.company.project.webapi.web.api.login;

import com.company.exception.entity.param.EmptyValueException;
import com.company.project.common.UserType;
import com.company.project.service.user.UserService;
import com.company.project.webapi.support.action.BaseAction;
import com.company.project.webapi.support.context.RequestContext;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 用户名密码登录
 *
 * @author wangzhj
 */
@Component
public class Action_login_by_smscode extends BaseAction {

    @Autowired
    private UserService userService;

    //登录名
    private String login_name;
    //验证码
    private String auth_code;
    //验证码Seq
    private String auth_code_seq;

    @Override
    public void checkData(RequestContext cxt, Map<String, Object> params) {
        login_name = getParam("login_name");
        if (Strings.isNullOrEmpty(login_name)) {
            throw new EmptyValueException("login_name");
        }
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
    public Map<String, Object> execute(RequestContext cxt, Map<String, Object> params) {
        Map<String, Object> data = userService.loginByAuthCode(login_name, auth_code, auth_code_seq, UserType.merchant);
        return data;
    }
}
