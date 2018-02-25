package com.company.project.webapi.web.api.user;

import com.company.project.dao.usermerchant.UserMerchantEO;
import com.company.project.service.merchant.MerchantService;
import com.company.project.webapi.support.context.RequestContext;
import com.company.project.webapi.support.action.BaseAction;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 获取用户信息
 *
 * @author wangzhj
 */
@Component
public class Action_get_use_info extends BaseAction {

    @Autowired
    private MerchantService merchantService;

    @Override
    public void checkData(RequestContext cxt, Map<String, Object> params) {
    }

    @Override
    public Map<String, Object> execute(RequestContext cxt, Map<String, Object> params) {
        UserMerchantEO umEO = merchantService.getMerchant(cxt.getUbId());
        Map<String, Object> data = Maps.newHashMap();
        data.put("name", umEO.getUbName());
        data.put("nick_name", umEO.getUmNickName());
        data.put("gender", umEO.getUbGender());
        data.put("province", umEO.getUmProvince());
        data.put("city", umEO.getUmCity());
        data.put("address", umEO.getUmAddress());
        return data;
    }
}
