package com.company.project.webapi.support;

import java.util.Map;

/**
 * 参数对象
 *
 * @author wangzhj
 */
public class Param {

    /* 参数Map */
    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 获取参数
     *
     * @param paramName
     * @return T
     */
    public <T> T get(String paramName) {
        return (T) paramMap.get(paramName);
    }
}
