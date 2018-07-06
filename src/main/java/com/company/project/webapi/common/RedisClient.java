package com.company.project.webapi.common;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

import javax.annotation.PostConstruct;

/**
 * 静态变量/类变量不是对象的属性，
 * 而是一个类的属性，spring则是基于对象层面上的依赖注入。
 *
 * @author wangzhj
 */
public class RedisClient {

    @Autowired
    private Pool<Jedis> pool;

    private static Pool<Jedis> POOL;

    //通过中间变量赋值
    @PostConstruct
    public void init() {
//        POOL = pool;
    }

    public static void set(String key, String value) {
        Jedis jedis = getJedis();
        try {
            jedis.set(key, value);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
//            jedis.close();
        }
    }

    private static Jedis getJedis() {
        return POOL.getResource();
    }

    //spring支持set方法注入，利用非静态setter 方法注入静态变量
//    @Autowired
//    public void setPool(Pool<Jedis> pool) {
//        POOL = pool;
//    }
}
