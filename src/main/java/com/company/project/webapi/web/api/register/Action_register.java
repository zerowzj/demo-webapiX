package com.company.project.webapi.web.api.register;

import com.company.exception.entity.param.EmptyValueException;
import com.company.exception.entity.param.FormatErrorException;
import com.company.project.dao.usermerchant.UserMerchantEO;
import com.company.project.service.merchant.MerchantService;
import com.company.project.webapi.support.action.BaseAction;
import com.company.project.webapi.support.context.RequestContext;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 注册
 *
 * @author wangzhj
 */
@Component
public class Action_register extends BaseAction {

    @Autowired
    private MerchantService merchantService;

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
        if (login_pwd.length() < 6 || login_pwd.length() > 20) {
            throw new FormatErrorException("login_pwd");
        }
    }

    @Override
    public Map<String, Object> execute(RequestContext cxt, Map<String, Object> params) {
        UserMerchantEO umEO = merchantService.addMerchant(login_name, login_pwd);
        Map<String, Object> data = Maps.newHashMap();
        data.put("ub_id", umEO.getUbId());
        return data;
    }
}
