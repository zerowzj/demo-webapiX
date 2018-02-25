package com.company.project.webapi.support;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 结果
 *
 * @author wangzhj
 */
public class Results {

    private static final String CODE_KEY = "code";

    private static final String DESC_KEY = "desc";

    private static final String DATA_KEY = "data";

    private static final String REQUEST_ID_KEY = "request_id";

    /**
     * 构造成功结果
     *
     * @param data
     * @return Map
     */
    public static Map<String, Object> buildOk(Map<String, Object> data) {
        return build("0000", "成功", data);
    }

    /**
     * 构造失败结果
     *
     * @param code
     * @param desc
     * @return Map
     */
    public static Map<String, Object> buildNotOk(String code, String desc) {
        return build(code, desc, null);
    }

    private static Map<String, Object> build(String code, String desc, Map<String, Object> data) {
        Map<String, Object> result = Maps.newHashMap();
        result.put(CODE_KEY, code);
        result.put(DESC_KEY, desc);
        if (data == null) {
            data = Maps.newHashMap();
        }
        result.put(DATA_KEY, data);
        //result.put(REQUEST_ID_KEY, TrackKey.get());
        return result;
    }
}
