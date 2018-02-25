package com.company.project.webapi.web.api.account;

import com.company.exception.entity.param.EmptyValueException;
import com.company.project.service.bankcard.BankCardService;
import com.company.project.webapi.support.action.BaseAction;
import com.company.project.webapi.support.context.RequestContext;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 获取用户信息
 *
 * @author wangzhj
 */
@Component
public class Action_bind_bank_card extends BaseAction {

    @Autowired
    private BankCardService bankCardService;

    //账户名
    private String account_name;
    //银行卡号
    private String bank_card_no;

    @Override
    public void checkData(RequestContext cxt, Map<String, Object> params) {
        account_name = getParam("account_name");
        if (Strings.isNullOrEmpty(account_name)) {
            throw new EmptyValueException("account_name");
        }
        bank_card_no = getParam("bank_card_no");
        if (Strings.isNullOrEmpty(bank_card_no)) {
            throw new EmptyValueException("bank_card_no");
        }
    }

    @Override
    public Map<String, Object> execute(RequestContext cxt, Map<String, Object> params) {
        bankCardService.addBankCard(cxt.getUbId(), account_name, bank_card_no);
        return null;
    }
}
