package com.mabel.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2021-12-22 21:00
 **/
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /***
     * 错误示范。。。
     * 该序列化器的配置只适用于key & value 都是String类型，如果是其他类型就会报类型转换错误
     * */
//    @Bean
    @Deprecated
    public RedisTemplate<String, String> redisTemplateOld() {
        StringRedisSerializer serializer = new StringRedisSerializer();
        StringRedisTemplate redisTemplate = new StringRedisTemplate(this.redisConnectionFactory);
        redisTemplate.setStringSerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(this.redisConnectionFactory);
        redisTemplate.setValueSerializer(valuesSerializer());
        return redisTemplate;
    }

    private Jackson2JsonRedisSerializer<Object> valuesSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }
}