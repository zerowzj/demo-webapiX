package com.test;

import com.company.project.webapi.web.api.user.Email;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by wangzhj on 2017/7/28.
 */

public class RedisTest {

    public static void main(String[] args) throws Exception{
        ObjectMapper mapper = new ObjectMapper(new SmileFactory());

        com.company.project.webapi.web.api.user.User user = new com.company.project.webapi.web.api.user.User();
        user.setName("wangzhj");
        user.setAge(33);
        user.setEmailLt(Arrays.asList(new com.company.project.webapi.web.api.user.Email("123123"), new Email("adsfsd")));
        byte[] smileData = mapper.writeValueAsBytes(user);

        Map str = mapper.readValue(smileData, Map.class);
        System.out.println(str);
    }
}
