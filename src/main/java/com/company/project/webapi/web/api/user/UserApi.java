package com.company.project.webapi.web.api.user;

import com.company.project.webapi.support.Api;
import com.company.project.webapi.support.action.ActionExecutor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 * 用户Api
 *
 * @author wangzhj
 */
@Api("/user")
public class UserApi {

    @RequestMapping("/modify_pwd")
    @ResponseBody
    public Map<String, Object> modify_user_pwd(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_modify_pwd.class);
    }

    @RequestMapping("/modify_user_info")
    @ResponseBody
    public Map<String, Object> modify_user_info(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_modify_user_info.class);
    }

    @RequestMapping("/get_user_info")
    @ResponseBody
    public Map<String, Object> get_user_info(HttpServletRequest request, HttpServletResponse response) {
        return ActionExecutor.execute(request, response, Action_get_use_info.class);
    }

    public static void main(String[] args) throws Exception{
        ObjectMapper mapper = new ObjectMapper(new SmileFactory());

        User user = new User();
        user.setName("wangzhj");
        user.setAge(33);
        user.setEmailLt(Arrays.asList(new Email("123123"), new Email("adsfsd")));
        byte[] smileData = mapper.writeValueAsBytes(user);



        Map str = mapper.readValue(smileData, Map.class);
        System.out.println(str);
    }
}
