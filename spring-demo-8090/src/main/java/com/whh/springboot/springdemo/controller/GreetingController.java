package com.whh.springboot.springdemo.controller;

import com.whh.AutoLog;
import com.whh.springboot.springdemo.domain.Greeting;
import com.whh.springboot.springdemo.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingController {
    @Autowired
    private GreetingService greetingService;
    @Autowired()
    @Qualifier("restTemplate1")
    private RestTemplate restTemplate;

    //    @Autowired
//    private EurekaClient discoveryClient;

    /**
     * http://localhost:8090/restTemplate
     *
     * @return
     */
    @RequestMapping("/restTemplate")
    public String restTemplate() {
        return restTemplate.getForObject("https://www.baidu.com/", String.class);
    }
    /**
     * http://localhost:8090/discovery?serviceName=demo
     * @param serviceName
     * @return
     */
//    @RequestMapping("/discovery")
//    @AutoLog("")
//    public InstanceInfo serviceUrl(@RequestParam("serviceName") String serviceName) {
//        InstanceInfo instance = discoveryClient.getNextServerFromEureka(serviceName, false);
//        return instance;
//    }

    /**
     * http://localhost:8090/greeting
     *
     * @param name
     * @return
     */
    @RequestMapping("/greeting")
    @AutoLog("")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return greetingService.greeting(name);
    }
}
