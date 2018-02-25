package com.company.project.webapi.web.api.login;

import com.company.exception.entity.param.EmptyValueException;
import com.company.exception.entity.param.FormatErrorException;
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
public class Action_login extends BaseAction {

    @Autowired
    private UserService userService;

    //登录名
    private String login_name;
    //登录密码
    private String login_pwd;

    @Override
    public void checkData(RequestContext cxt, Map<String, Object> params) {
        login_name = getParam("login_name");
        if (Strings.isNullOrEmpty(login_name)) {
            throw new EmptyValueException("login_name");
        }
        if (login_name.length() != 11) {
            throw new FormatErrorException("login_name");
        }
        login_pwd = getParam("login_pwd");
        if (Strings.isNullOrEmpty(login_pwd)) {
            throw new EmptyValueException("login_pwd");
        }
        if (login_pwd.length() > 20 || login_pwd.length() < 6) {
            throw new FormatErrorException("login_pwd");
        }
    }

    @Override
    public Map<String, Object> execute(RequestContext cxt, Map<String, Object> params) {
        Map<String, Object> data = userService.login(login_name, login_pwd, UserType.merchant);
        return data;
    }
}
