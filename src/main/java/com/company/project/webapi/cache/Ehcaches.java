package com.company.project.webapi.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Ehcaches {

    private static final String FILE = "";

    private static CacheManager CACHE_MANAGE;

    static {
        CACHE_MANAGE = CacheManager.create();
    }

    /**
     * 获取缓存
     *
     * @param cacheName
     * @return Cache
     */
    public Cache getCache(String cacheName) {
        return CACHE_MANAGE.getCache(cacheName);
    }

    /**
     * 设置值
     *
     * @param key
     * @param obj
     */
    public <T> void put(String key, T obj) {
        Element ele = new Element(key, obj);
    }

    /**
     * 获取值
     *
     * @param key
     * @return T
     */
    public <T> T get(String key) {
        Element ele = getCache("").get(key);
        return (T) ele.getObjectValue();
    }
}
