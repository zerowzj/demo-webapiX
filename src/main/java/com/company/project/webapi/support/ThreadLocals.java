package com.company.project.webapi.support;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 线程本地存储
 *
 * @author wangzhj
 */
public class ThreadLocals {

    private static ThreadLocal<Map<String, Object>> LOCAL = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return Maps.newHashMap();
        }
    };

    private static final String KEY_TRACK_KEY = "TRACK_KEY";

    /**
     * 设置Track Key
     *
     * @param trackKey
     */
    public static void setTrackKey(String trackKey) {
        get().put(KEY_TRACK_KEY, trackKey);
    }

    /**
     * 获取Track Key
     *
     * @return String
     */
    public static String getTrackKey() {
        return (String) get().get(KEY_TRACK_KEY);
    }

    /**
     * 移除Track Key
     *
     * @return String
     */
    public static void removeTrackKey() {
        get().remove(KEY_TRACK_KEY);
    }

    private static Map<String, Object> get() {
        return LOCAL.get();
    }
}
