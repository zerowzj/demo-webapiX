package com.company.project.webapi.web.api.register;

import com.company.exception.entity.business.WrongException;
import com.company.exception.entity.param.EmptyValueException;
import com.company.exception.entity.param.FormatErrorException;
import com.company.project.dao.userbase.UserBaseEO;
import com.company.project.service.sms.AuthCodeService;
import com.company.project.service.user.UserService;
import com.company.project.webapi.support.action.BaseAction;
import com.company.project.webapi.support.context.RequestContext;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 重置密码
 *
 * @author wangzhj
 */
@Component
public class Action_reset_pwd extends BaseAction {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthCodeService authCodeService;

    //登录名
    private String login_name;
    //验证码
    private String sms_code;
    //验证码Seq
    private String sms_code_seq;
    //新密码
    private String new_pwd;

    public Action_reset_pwd() {
    }

    @Override
    public void checkData(RequestContext cxt, Map<String, Object> params) {
        login_name = getParam("login_name");
        if (Strings.isNullOrEmpty(login_name)) {
            throw new EmptyValueException("login_name");
        }
        sms_code = getParam("sms_code");
        if (Strings.isNullOrEmpty(sms_code)) {
            throw new EmptyValueException("sms_code");
        }
        if (sms_code.length() != 6) {
            throw new FormatErrorException("sms_code");
        }
        sms_code_seq = getParam("sms_code_seq");
        if (Strings.isNullOrEmpty(sms_code_seq)) {
            throw new EmptyValueException("sms_code_seq");
        }
        new_pwd = getParam("new_pwd");
        if (Strings.isNullOrEmpty(sms_code)) {
            throw new EmptyValueException("new_pwd");
        }
        if (new_pwd.length() < 6 || new_pwd.length() > 20) {
            throw new FormatErrorException("new_pwd");
        }
    }

    @Override
    public Map<String, Object> execute(RequestContext cxt, Map<String, Object> params) {
        boolean isOk = authCodeService.verifyAuthCode(sms_code, sms_code_seq);
        if (!isOk) {
            throw new WrongException("验证码");
        }

        UserBaseEO ubEO = userService.getUser(cxt.getUbId());
        ubEO.setUbLoginPwd(new_pwd);

        return null;
    }
}
