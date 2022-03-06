package com.whh.springboot.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class SpringDemoApplication  {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }
}