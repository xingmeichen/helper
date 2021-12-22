package com.mabel.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2021-12-22 21:00
 **/
//@Configuration
//@EnableCaching
//TODO
public class RedisConfiguration extends CachingConfigurerSupport {

//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;

//    @Bean
//    public RedisTemplate<String, String> redisTemplate() {
//
//    }
}