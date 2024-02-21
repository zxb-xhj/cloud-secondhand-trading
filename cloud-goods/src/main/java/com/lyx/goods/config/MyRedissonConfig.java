package com.lyx.goods.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: xhj
 * @Date: 2023/11/15/18:58
 * @Description:
 */
@Configuration
public class MyRedissonConfig {

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://121.37.208.253:6329");
        return Redisson.create(config);
    }
}
