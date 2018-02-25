package com.company.project.webapi.web.api.account;

import com.company.exception.entity.param.EmptyValueException;
import com.company.exception.entity.param.ValueIllegalException;
import com.company.project.service.account.WithdrawalService;
import com.company.project.webapi.support.action.BaseAction;
import com.company.project.webapi.support.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 申请提现
 *
 * @author wangzhj
 */
@Component
public class Action_apply_for_cash extends BaseAction {

    @Autowired
    private WithdrawalService withdrawalService;

    //申请金额
    private Double apply_amt;

    @Override
    public void checkData(RequestContext cxt, Map<String, Object> params) {
        apply_amt = getParam("apply_amt");
        if (apply_amt == null) {
            throw new EmptyValueException("apply_amt");
        }
        if (apply_amt < 100 || apply_amt > 1000) {
            throw new ValueIllegalException("apply_amt");
        }
    }

    @Override
    public Map<String, Object> execute(RequestContext cxt, Map<String, Object> params) {
        withdrawalService.addWithdrawal(cxt.getUbId(), apply_amt);
        return null;
    }
}
