package com.company.project.webapi.web.api.account;

import com.company.project.webapi.support.Api;
import com.company.project.webapi.support.action.ActionExecutor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 账户Api
 *
 * @author wangzhj
 */
@Api("/account")
public class AccountApi {

    @RequestMapping("/bind_bank_card")
    @ResponseBody
    public Map<String, Object> bind_bank_card(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_bind_bank_card.class);
    }

    @RequestMapping("/unbind_bank_card")
    @ResponseBody
    public Map<String, Object> unbind_bank_card(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_unbind_bank_card.class);
    }

    @RequestMapping("/get_bank_card")
    @ResponseBody
    public Map<String, Object> get_bank_card(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_get_bank_card.class);
    }

    @RequestMapping("/apply_for_cash")
    @ResponseBody
    public Map<String, Object> apply_for_cash(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_apply_for_cash.class);
    }

    @RequestMapping("/get_bill_list")
    @ResponseBody
    public Map<String, Object> get_bill_list(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_get_bill_list.class);
    }
}
