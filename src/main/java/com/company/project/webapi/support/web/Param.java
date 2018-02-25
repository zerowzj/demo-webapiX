package com.company.project.webapi.support.web;

import java.util.Map;

/**
 * 参数对象
 *
 * @author wangzhj
 */
public class Param {

    /* 参数Map */
    private Map<String, Object> param;

    public Param(Map<String, Object> param) {
        this.param = param;
    }

    /**
     * 获取参数
     *
     * @param paramName
     * @return T
     */
    public <T> T get(String paramName) {
        return (T) param.get(paramName);
    }
}
