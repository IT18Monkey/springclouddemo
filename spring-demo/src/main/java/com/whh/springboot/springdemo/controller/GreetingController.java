package com.whh.springboot.springdemo.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.whh.AutoLog;
import com.whh.LogService;
import com.whh.springboot.springdemo.domain.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private AtomicLong counter=new AtomicLong();
    @Autowired
    private EurekaClient discoveryClient;

    /**
     * http://localhost:8090/discovery?serviceName=demo
     * @param serviceName
     * @return
     */
    @RequestMapping("/discovery")
    @AutoLog("")
    public InstanceInfo serviceUrl(@RequestParam("serviceName") String serviceName) {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka(serviceName, false);
        return instance;
    }

    /**
     * http://localhost:8090/greeting
     * @param name
     * @return
     */
    @RequestMapping("/greeting")
    @AutoLog("")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name,@RequestParam(value="value",required = false) String value) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}
