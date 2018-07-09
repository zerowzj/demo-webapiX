package com.company.project.webapi.auth;

public class TrackKeys {

    private static ThreadLocal<String> LOCAL = new ThreadLocal<>();

    public static void set(String trackKey) {
        LOCAL.set(trackKey);
    }

    public static String get() {
        return LOCAL.get();
    }

    public static void clear() {
        LOCAL.remove();
    }
}
