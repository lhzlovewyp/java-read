package com.sea.common.cache.redis;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lvhaizhen on 2018/7/25.
 */
@Configuration
@EnableCaching
public class RedisCacheConfig  extends CachingConfigurerSupport {


}
