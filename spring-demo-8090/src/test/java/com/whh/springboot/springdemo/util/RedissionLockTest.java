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
public class RedissionLockTest {
    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testLock() throws InterruptedException {
        RLock lock = redissonClient.getLock("redission");
//        lock.lock(5, TimeUnit.SECONDS);
        boolean res = lock.tryLock(100, TimeUnit.SECONDS);//自动续期
        if (res) {
            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
