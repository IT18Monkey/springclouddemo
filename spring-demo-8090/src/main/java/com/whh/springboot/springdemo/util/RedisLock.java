package com.whh.springboot.springdemo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 自己实现redis分布式锁，问题较多，不可靠
 *
 */
@Component
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     *
     * @param name key
     * @param expire key过期时间
     * @param timeOut 重试超时时间
     * @return
     */
    public boolean tryLock(String name, Long expire, Long timeOut) {
        /**
         * 随机生成唯一id，释放锁是需判断是否相同
         */
        String value = UUID.randomUUID().toString();
        Long lockTime = System.currentTimeMillis();
        while (true) {
            if (redisTemplate.opsForValue().setIfAbsent(name, value, expire, TimeUnit.SECONDS)) {
                return true;
            } else {
                if ((System.currentTimeMillis() - lockTime)/1000 > timeOut) {
                    return false;
                }
                try {
                    //睡眠一秒重试
                    TimeUnit.SECONDS.sleep(1l);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    /**
     * 先比较value值，相同再删除
     *
     * @param name
     */
    public void releaseLock(String name) {
        redisTemplate.opsForValue().getOperations().delete(name);
    }
}
