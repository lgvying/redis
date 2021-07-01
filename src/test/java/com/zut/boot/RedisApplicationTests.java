package com.zut.boot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zut.boot.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.Set;

@SpringBootTest
class RedisApplicationTests {
    @Autowired
    //引入模板类
    private RedisTemplate<String,Object> redisTemplate;

    /**redisTemplate.opsForValue()//操作string K-V
     * redisTemplate.opsForHash();//操作hash
     * redisTemplate.opsForSet();//操作set
     * redisTemplate.opsForZSet();//操作zset
     * redisTemplate.opsForList();//操作list
     */

    @Test
    void contextLoads() {
    }
    @Test
    void test1() {
        redisTemplate.opsForValue().set("key1","key_value");
        System.out.println(redisTemplate.opsForValue().get("key1"));
    }
    @Test
    void test2() {
        //存放的对象必须实现序列化：不然会抛出异常：SerializationException
        Student student=new Student(1001,"韩梅梅","女",11f);
        redisTemplate.opsForValue().set("key2",student);
        System.out.println(redisTemplate.opsForValue().get("key2"));
    }
    //如果对象没有实现序列化  但也想存储到redis中
    //方式1：把对象对应的json存入数据库中
    @Test
    void test3() throws Exception{
        //创建对象
        Student student=new Student(1001,"韩梅梅","女",11f);
        //获取对应对象对应的json串
        String str=new ObjectMapper().writeValueAsString(student);
        System.out.println("str--"+str);
        //将json串放入模板中
        redisTemplate.opsForValue().set("key3",str);
        //从模板中获取键对应的值（json串）
        String key3 = (String) redisTemplate.opsForValue().get("key3");
        System.out.println("key3--"+key3);
        //把json串转换为对象
        Student student1 = new ObjectMapper().readValue(str, Student.class);
        System.out.println("student1--"+student1);
    }

}
