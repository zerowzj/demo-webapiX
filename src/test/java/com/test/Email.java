
package com.test;

import java.io.Serializable;

/**
 * Created by wangzhj on 2017/7/28.
 */
public class Email implements Serializable {

    private String email;

    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
