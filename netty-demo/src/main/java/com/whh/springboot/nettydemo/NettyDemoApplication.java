package com.whh.springboot.nettydemo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class NettyDemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(NettyDemoApplication.class).web(WebApplicationType.NONE).run(args);
    }
}
