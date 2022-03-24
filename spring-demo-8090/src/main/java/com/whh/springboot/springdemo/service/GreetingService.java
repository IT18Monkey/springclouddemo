package com.whh.springboot.springdemo.service;

import com.whh.springboot.springdemo.domain.Greeting;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
@Service
public class GreetingService {
    private static final String template = "Hello, %s!";
    private AtomicLong counter = new AtomicLong();
    @Cacheable(value = "greet",key ="'name_'+#name")
    public Greeting greeting(String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

}
