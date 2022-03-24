package com.whh.springboot.springdemo.controller;

import com.whh.springboot.springdemo.domain.Greeting;
import com.whh.springboot.springdemo.service.GreetingService;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {

    @Autowired
    private MockMvc mvc;   //注入MockMvc

    @Autowired
    private GreetingService greetingService;
    @Test
    public void greeting() throws Exception {
        BDDMockito.given(this.greetingService.greeting("world")).willReturn(new Greeting(1, "world"));
        this.mvc.perform(get("/greeting").accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk()).andExpect(content().json("{\"id\":1,\"content\":\"Hello, world!\"}"));
    }
}