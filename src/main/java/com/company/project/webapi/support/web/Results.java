package com.company.project.webapi.support.web;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 结果
 *
 * @author wangzhj
 */
public class Results {

    private static final String KEY_CODE = "code";

    private static final String KEY_DESC = "desc";

    private static final String KEY_DATA = "data";

    private static final String KEY_REQUEST_ID = "request_id";

    /**
     * 成功结果
     *
     * @param data
     * @return Map
     */
    public static Map<String, Object> ok(Map<String, Object> data) {
        return build("0000", "成功", data);
    }

    /**
     * 错误结果
     *
     * @param code
     * @param desc
     * @return Map
     */
    public static Map<String, Object> error(String code, String desc) {
        return build(code, desc, null);
    }

    private static Map<String, Object> build(String code, String desc, Map<String, Object> data) {
        Map<String, Object> result = Maps.newHashMap();
        result.put(KEY_CODE, code);
        result.put(KEY_DESC, desc);
        if (data == null) {
            data = data();
        }
        result.put(KEY_DATA, data);
        //result.put(KEY_REQUEST_ID, TrackKeys.get());
        return result;
    }

    /**
     * Data Map
     *
     * @return Map
     */
    public static Map<String, Object> data() {
        return Maps.newHashMap();
    }
}
