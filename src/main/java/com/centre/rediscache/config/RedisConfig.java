package com.centre.rediscache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.experimental.var;

@Configuration
@EnableCaching
public class RedisConfig {
	
	
	@Bean
	JedisConnectionFactory jeddConnectionFactory()
	{
		RedisStandaloneConfiguration redisStandaloneConfiguration= new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName("localhost");
		redisStandaloneConfiguration.setPort(6379);
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}
	
	@Bean
    RedisTemplate<String, Object> redisTemplate()
	{
		var redistemplate1= new RedisTemplate<String, Object>();
		redistemplate1.setConnectionFactory(jeddConnectionFactory());
		redistemplate1.setKeySerializer(new StringRedisSerializer());
		redistemplate1.setValueSerializer(new StringRedisSerializer());
		return redistemplate1;
	}
	
	@Bean
	 ObjectMapper objectMapper()
	{
		ObjectMapper objectMapper= new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return objectMapper;
	}

}
