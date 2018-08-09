package com.sea.cache.jedis;

import com.sea.controller.BackendLoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

/**
 * Created by lvhaizhen on 2018/7/26.
 */
@Component("jedisUtil")
public class JedisUtil {

    private static Logger LOG = LoggerFactory.getLogger(JedisUtil.class);


    @Autowired
    private JedisPool jedisPool;

    public void remove(final String... keys) {
        try{
            jedisPool.getResource().del(keys);
        }catch (Exception e){
            LOG.error("jedis error:",e);
        }

    }

    public String get(final String key) {
        jedisPool.getResource().get(key);

        //jedisPool.getResource().
        return null;
    }
}
