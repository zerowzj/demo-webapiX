package com.company.project.webapi.web.api.account;

import com.company.project.dao.accountbankcard.AccountBankCardEO;
import com.company.project.service.bankcard.BankCardService;
import com.company.project.webapi.support.action.BaseAction;
import com.company.project.webapi.support.context.RequestContext;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 获取用户信息
 *
 * @author wangzhj
 */
@Component
public class Action_get_bank_card extends BaseAction {

    @Autowired
    private BankCardService bankCardService;

    @Override
    public void checkData(RequestContext cxt, Map<String, Object> params) {

    }

    @Override
    public Map<String, Object> execute(RequestContext cxt, Map<String, Object> params) {
        List<AccountBankCardEO> cardLt = bankCardService.getBankCardLt(cxt.getUbId());
        Map<String, Object> data = Maps.newHashMap();
        if(cardLt != null && !cardLt.isEmpty()){
            data.put("bank_card_no", cardLt.get(0).getAbcBankCardNo());
        }
        return data;
    }
}
