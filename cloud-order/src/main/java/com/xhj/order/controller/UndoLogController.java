package com.xhj.order.controller;

import com.lyx.common.base.result.R;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xhj
 * @Date: 2023/04/24/14:28
 * @Description:
 */
@RestController
@RequestMapping("/undolog")
public class UndoLogController {

    @Autowired
    RedissonClient redissonClient;

    @GetMapping("/test")
    public R test(){
        RSemaphore semaphore = redissonClient.getSemaphore("goods:" + 2);
        semaphore.tryAcquire();

        return R.ok(semaphore.availablePermits());
    }
    @GetMapping("/test1")
    public R test1(){
        RSemaphore semaphore = redissonClient.getSemaphore("goods:" + 2);
        semaphore.release();
        return R.ok(semaphore.availablePermits());
    }

}
