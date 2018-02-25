package com.company.project.webapi.web.api.user;

import java.util.List;

/**
 * Created by wangzhj on 2017/7/28.
 */
public class User {

    private String name;

    private int age;

    private List<Email> emailLt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Email> getEmailLt() {
        return emailLt;
    }

    public void setEmailLt(List<Email> emailLt) {
        this.emailLt = emailLt;
    }
}
