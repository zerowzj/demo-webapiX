package com.company.project.webapi.web.api.account;

import com.company.exception.entity.param.EmptyValueException;
import com.company.project.service.bankcard.BankCardService;
import com.company.project.webapi.support.action.BaseAction;
import com.company.project.webapi.support.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 获取用户信息
 *
 * @author wangzhj
 */
@Component
public class Action_unbind_bank_card extends BaseAction {

    @Autowired
    private BankCardService bankCardService;

    //银行卡编号
    private Long abc_id;

    @Override
    public void checkData(RequestContext cxt, Map<String, Object> params) {
        abc_id = getParam("abc_id");
        if (abc_id == null || abc_id == 0) {
            throw new EmptyValueException("abc_id");
        }
    }

    @Override
    public Map<String, Object> execute(RequestContext cxt, Map<String, Object> params) {
        return null;
    }
}
