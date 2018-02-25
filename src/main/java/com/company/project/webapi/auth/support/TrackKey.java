package com.company.project.webapi.auth.support;

/**
 * Track Key Holder
 *
 * @author wangzhj
 */
public abstract class TrackKey {

    private static ThreadLocal<String> LOCAL = new ThreadLocal();

    private TrackKey() {
    }

    public static String get() {
        return (String) LOCAL.get();
    }

    public static void set(String traceKey) {
        LOCAL.set(traceKey);
    }

    public static void remove() {
        LOCAL.remove();
    }
}
