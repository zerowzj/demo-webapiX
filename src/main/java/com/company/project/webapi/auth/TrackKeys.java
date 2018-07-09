package com.company.project.webapi.auth;

public class TrackKeys {

    private static ThreadLocal<String> LOCAL = new ThreadLocal<>();

    /**
     * 设置
     *
     * @param trackKey
     */
    public static void set(String trackKey) {
        LOCAL.set(trackKey);
    }

    /**
     * 获取
     *
     * @return String
     */
    public static String get() {
        return LOCAL.get();
    }

    /**
     * 移除
     */
    public static void remove() {
        LOCAL.remove();
    }
}
