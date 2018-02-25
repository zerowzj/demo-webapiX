package com.company.project.webapi.web.api.user;

import com.company.exception.entity.business.NotExistException;
import com.company.exception.entity.business.WrongException;
import com.company.exception.entity.param.EmptyValueException;
import com.company.project.dao.userbase.UserBaseEO;
import com.company.project.service.user.UserService;
import com.company.project.webapi.support.action.BaseAction;
import com.company.project.webapi.support.context.RequestContext;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 修改用户密码
 *
 * @author wangzhj
 */
@Component
public class Action_modify_pwd extends BaseAction {

    @Autowired
    private UserService userService;

    //旧密码
    private String old_pwd;
    //新密码
    private String new_pwd;

    @Override
    public void checkData(RequestContext cxt, Map<String, Object> params) {
        old_pwd = getParam("old_pwd");
        if (Strings.isNullOrEmpty(old_pwd)) {
            throw new EmptyValueException("old_pwd");
        }
        new_pwd = getParam("new_pwd");
        if (Strings.isNullOrEmpty(new_pwd)) {
            throw new EmptyValueException("new_pwd");
        }
    }

    @Override
    public Map<String, Object> execute(RequestContext cxt, Map<String, Object> params) {
        UserBaseEO ubEO = userService.getUser(cxt.getUbId());
        if(ubEO == null){
            throw new NotExistException("用户");
        }
        if (!Objects.equal(old_pwd, ubEO.getUbLoginPwd())) {
            throw new WrongException("原密码");
        }
        ubEO.setUbLoginPwd(new_pwd);
        userService.modifyUser(ubEO);
        return null;
    }
}
