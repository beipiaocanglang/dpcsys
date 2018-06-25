package dpcsys.consumption.dpcnmm.redisManager;

import org.springframework.beans.factory.annotation.Autowired;

import faner.dplatform.api.frame.redis.JedisService;
import faner.dplatform.api.frame.redis.RedisService;

public class RedisManager {
	
	@Autowired(required=false)
    private RedisService redisService;
	
	@Autowired(required=false)
    private JedisService jedisService;
}
