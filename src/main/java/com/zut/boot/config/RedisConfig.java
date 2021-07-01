package com.zut.boot.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author 梁贵莹
 * @create 2021/7/1 下午 7:32
 */
@Configuration
public class RedisConfig {
    //方式二：自定义redisTemplate组件
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> template=new RedisTemplate<>();
        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectJackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        template.setConnectionFactory(factory);
        //key序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        //value序列化
        template.setValueSerializer(objectJackson2JsonRedisSerializer);
        //value hashmap序列化
        template.setHashValueSerializer(objectJackson2JsonRedisSerializer);
        return template;
    }
}
