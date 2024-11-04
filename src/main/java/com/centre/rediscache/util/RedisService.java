package com.centre.rediscache.util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RedisService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	public void setValueByKey(String key, Object object, Long ttl) throws JsonProcessingException {
		String valueTosaved = objectMapper.writeValueAsString(object);
		redisTemplate.opsForValue().set(key, valueTosaved, ttl, TimeUnit.SECONDS);
	}

	public <T> T getValueByKey(String key, Class<T> type) {
		Object object = redisTemplate.opsForValue().get(key);
		try {
			return object != null ? objectMapper.readValue(object.toString(), type) : null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean deleteFromCache(String key)
	{
		return Boolean.TRUE.equals(redisTemplate.delete(key));
	}

	public long deltetMultipleRecodsFromcache(List<String> keys)
	{
		Long delete = redisTemplate.delete(keys);
		return delete == null ? 0 : delete;
	}
	
}
