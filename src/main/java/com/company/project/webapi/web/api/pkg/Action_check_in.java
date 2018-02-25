package com.company.project.webapi.web.api.pkg;

import com.company.exception.entity.param.EmptyValueException;
import com.company.exception.entity.param.FormatErrorException;
import com.company.project.dao.packagebase.PackageBaseEO;
import com.company.project.service.pkg.PackageService;
import com.company.project.webapi.support.action.BaseAction;
import com.company.project.webapi.support.context.RequestContext;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 登记包裹
 *
 * @author wangzhj
 */
@Component
public class Action_check_in extends BaseAction {

    @Autowired
    private PackageService packageService;

    //收件人手机号
    private String receiver_phone;

    @Override
    public void checkData(RequestContext cxt, Map<String, Object> params) {
        receiver_phone = getParam("receiver_phone");
        if (Strings.isNullOrEmpty(receiver_phone)) {
            throw new EmptyValueException("receiver_phone");
        }
        if (receiver_phone.length() != 11) {
            throw new FormatErrorException("receiver_phone");
        }
    }

    @Override
    public Map<String, Object> execute(RequestContext cxt, Map<String, Object> params) {
        PackageBaseEO pbEO = packageService.addPackage(cxt.getUbId(), receiver_phone);
        Map data = Maps.newHashMap();
        data.put("get_code", pbEO.getPbGetCode());
        return data;
    }
}
