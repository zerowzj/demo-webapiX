package com.company.project.webapi.web.api.user;

import com.company.exception.entity.param.EmptyValueException;
import com.company.project.dao.usermerchant.UserMerchantEO;
import com.company.project.service.merchant.MerchantService;
import com.company.project.webapi.support.context.RequestContext;
import com.company.project.webapi.support.action.BaseAction;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 修改用户信息
 *
 * @author wangzhj
 */
@Component
public class Action_modify_user_info extends BaseAction {

    @Autowired
    private MerchantService merchantService;

    //昵称
    private String nick_name;

    @Override
    public void checkData(RequestContext cxt, Map<String, Object> params) {
        nick_name = getParam("nick_name");
        if (Strings.isNullOrEmpty(nick_name)) {
            throw new EmptyValueException("nick_name");
        }
    }

    @Override
    public Map<String, Object> execute(RequestContext cxt, Map<String, Object> params) {
        UserMerchantEO umEO = merchantService.getMerchant(cxt.getUbId());
        umEO.setUmNickName(nick_name);
        merchantService.modifyMerchant(umEO);
        return null;
    }
}
