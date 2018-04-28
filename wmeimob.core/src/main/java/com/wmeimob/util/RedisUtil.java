package com.wmeimob.util;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * redis工具类
 * 	新增factory构造函数 用于管理通用的微信token
 * @author zJun
 * @date 2017年9月25日 下午2:09:33
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class RedisUtil {

	private StringRedisTemplate stringRedisTemplate;

	private RedisTemplate redisTemplate;
	
	public RedisUtil(){}
	
	public RedisUtil(JedisConnectionFactory factory) {
		stringRedisTemplate = new StringRedisTemplate(factory);
		setSerializer(); // 设置序列化工具，这样ReportBean不需要实现Serializable接口
		stringRedisTemplate.afterPropertiesSet();
		redisTemplate = stringRedisTemplate;
	}
	
	private void setSerializer() {
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		stringRedisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
	}
	
	public String getString(String key){
		return stringRedisTemplate.opsForValue().get(key);
	}
	
	public void setValue(String key, String value){
		stringRedisTemplate.opsForValue().set(key, value);
	}
	
	/**
	 * 设置value，在seconds秒后失效
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public void setValue(String key, String value, int seconds){
		stringRedisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
	}
	
	/**
	 * 删除key
	 * @param key
	 * @author zJun
	 * @date 2018年4月26日 下午3:37:30
	 */
	public void remove(String key) {
	    stringRedisTemplate.opsForValue().set(key, null, 1, TimeUnit.SECONDS);
	}
	
	public <T> void setValue(String key, T value){
		ValueOperations<String, T> operations=redisTemplate.opsForValue();
		operations.set(key, value);
	}
	
	/**
	 * 设置value，在seconds秒后失效
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public <T> void setTValue(String key, T value, int seconds){
		ValueOperations<String, T> operations=redisTemplate.opsForValue();
		operations.set(key, value, seconds, TimeUnit.SECONDS);
	}
	
	public <T> T get(String key){
		ValueOperations<String, T> operations=redisTemplate.opsForValue();
		return operations.get(key);
	}

}
