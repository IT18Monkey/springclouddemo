package com.whh.springboot.springdemo.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisLockTest {

    @Autowired
    private RedisLock redisLock;
    @Test
    public void tryLock() {
        String key = "redisLock";
        redisLock.tryLock(key, 60L, 10L);
        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            redisLock.releaseLock(key);
        }
    }

}