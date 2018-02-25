package com.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by wangzhj on 2017/7/28.
 */

public class RedisTest2 {

    private ObjectMapper mapper;

    private Jedis jedis;

    private byte[] key = "USER".getBytes();

    @Before
    public void before() {
        mapper = new ObjectMapper(new SmileFactory());

        JedisPoolConfig config = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);
        jedis = (Jedis) jedisPool.getResource();
    }

    @Test
    public void test_set() throws Exception {
        User user = new User();
        user.setName("wangzhj");
        user.setAge(33);
//        user.setSex("男");

        byte[] smileData = mapper.writeValueAsBytes(user);
        String smile = mapper.writeValueAsString(user);
        System.out.println(smile);


        jedis.set(key, smileData);
    }

    @Test
    public void test_get() throws Exception {
        byte[] smileData = jedis.get(key);

        User user = mapper.readValue(smileData, User.class);

        System.out.println(user.getName());
        System.out.println(user.getAge());
    }
}
