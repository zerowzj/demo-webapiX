package com.company.project.webapi.cache;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 * @author wangzhj
 */
public class RedisClient {

    private static Pool<Jedis> POOL;

    public static void set(String key, String value) {
        getJedis().set(key, value);
    }

    private static Jedis getJedis() {
        return POOL.getResource();
    }

    @Autowired
    public void setPool(Pool<Jedis> pool) {
        POOL = pool;
    }
}
